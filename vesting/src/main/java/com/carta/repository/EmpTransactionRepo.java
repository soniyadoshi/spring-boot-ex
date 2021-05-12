package com.carta.repository;

import com.carta.model.EmpTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpTransactionRepo extends JpaRepository<EmpTransaction, Long> {
}
