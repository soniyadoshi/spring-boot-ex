package com.carta.controller;

import com.carta.model.EmpTransaction;
import com.carta.service.VestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "api/vesting")
public class EmpVestingController {

    @Autowired
    VestingService vestingService;

    @GetMapping("/getShares/{targetDate}/{scale}")
    public ResponseEntity<List<EmpTransaction>> getPerAwardShares(@PathVariable(value = "targetDate") String targetDate, @PathVariable(value = "scale", required = false)Integer scale) {

        try {

            LocalDate localDate = LocalDate.parse(targetDate);
            List<EmpTransaction> transactions = vestingService.getPerAwardShares(localDate, scale);

            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmpTransaction>> getAllTransactions() {

        try {
            List<EmpTransaction> transactions = vestingService.getAllTransactions();

            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
