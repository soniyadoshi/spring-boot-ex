package com.carta.service;

import com.carta.model.EmpTransaction;

import java.time.LocalDate;
import java.util.List;

public interface VestingService {

    public List<EmpTransaction> getAllTransactions();
    public List<EmpTransaction> getPerAwardShares(LocalDate targetDate, Integer scale);
}
