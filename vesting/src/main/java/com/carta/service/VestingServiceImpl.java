package com.carta.service;

import com.carta.comparators.SortByAwardId;
import com.carta.comparators.SortByEmployeeId;
import com.carta.model.EmpTransaction;
import com.carta.repository.EmpTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

@Service
public class VestingServiceImpl implements VestingService {

    @Autowired
    EmpTransactionRepo repository;

    @Override
    public List<EmpTransaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public List<EmpTransaction> getPerAwardShares(LocalDate targetDate, Integer scale) {

        return filterSharesByDate(targetDate, scale);
    }

    private List<EmpTransaction> filterSharesByDate(LocalDate targetDate, Integer scale) {

        List<EmpTransaction> allEmployees = getAllTransactions();

        Map<String, BigDecimal> shareCountMap = new HashMap<>();

        for (EmpTransaction employee : allEmployees) {

            String key = employee.getEmployeeId() + "%" + employee.getEmployeeName() + "%" + employee.getAwardId();
            BigDecimal value = employee.getQuantity();

            BigDecimal bdQuantity = value.setScale(scale, RoundingMode.DOWN);

            if (employee.getVestingDate().isBefore(targetDate) || employee.getVestingDate().isEqual(targetDate)) {

                if (employee.getTransactionType().equalsIgnoreCase("VEST")) {
                    shareCountMap.put(key, shareCountMap.getOrDefault(key, BigDecimal.ZERO).add(bdQuantity));
                } else if (employee.getTransactionType().equalsIgnoreCase("CANCEL")) {
                    shareCountMap.put(key, shareCountMap.getOrDefault(key, BigDecimal.ZERO).subtract(bdQuantity));
                }

            } else if (!shareCountMap.containsKey(key)) {

                shareCountMap.put(key, BigDecimal.ZERO);
            }

        }

        return buildResult(shareCountMap, allEmployees, scale);
    }

    private List<EmpTransaction> buildResult(Map<String, BigDecimal> shareCountMap, List<EmpTransaction> allEmployees, Integer scale) {

        List<EmpTransaction> result = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : shareCountMap.entrySet()) {

            String key = entry.getKey();
            String[] employees = key.split("%");

            String empId = employees[0];
            String empName = employees[1];
            String awardId = employees[2];
            BigDecimal quantity = entry.getValue();

            BigDecimal bdQuantity = quantity.setScale(scale, RoundingMode.UP);

            EmpTransaction emp = new EmpTransaction(empId, empName, awardId, bdQuantity);

            result.add(emp);
        }

        Collections.sort(result, new SortByEmployeeId());
        Collections.sort(result, new SortByAwardId());

        return result;
    }

}
