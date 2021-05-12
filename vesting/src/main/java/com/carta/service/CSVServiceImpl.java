package com.carta.service;

import com.carta.helper.CSVHelper;
import com.carta.model.EmpTransaction;
import com.carta.repository.EmpTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CSVServiceImpl implements CSVService {

    @Autowired
    EmpTransactionRepo repository;

    @Override
    @Transactional
    public void save(MultipartFile file) {
        try {
            List<EmpTransaction> transactions = CSVHelper.csvToTransactions(file.getInputStream());
            repository.deleteAll();
            repository.saveAll(transactions);
        } catch (Exception e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
