package com.carta.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="EmpTransaction")
public class EmpTransaction {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long transactionId;

    private String transactionType;

    private String employeeId;

    private String employeeName;

    private String awardId;

    private LocalDate vestingDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(precision = 15, scale = 6)
    private BigDecimal quantity;



    public EmpTransaction(Long transactionId, String transactionType, String employeeId, String employeeName, String awardId, LocalDate vestingDate, BigDecimal quantity) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.awardId = awardId;
        this.vestingDate = vestingDate;
        this.quantity = quantity;
    }

    public EmpTransaction(String transactionType, String employeeId, String employeeName, String awardId, LocalDate vestingDate, BigDecimal quantity) {
        this.transactionType = transactionType;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.awardId = awardId;
        this.vestingDate = vestingDate;
        this.quantity = quantity;
    }

    public EmpTransaction(String employeeId, String employeeName, String awardId, BigDecimal quantity) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.awardId = awardId;
        this.quantity = quantity;
    }

    public EmpTransaction() {
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public LocalDate getVestingDate() {
        return vestingDate;
    }

    public void setVestingDate(LocalDate vestingDate) {
        this.vestingDate = vestingDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "EmpTransaction{" +
                "transactionId=" + transactionId +
                ", transactionType='" + transactionType + '\'' +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", awardId='" + awardId + '\'' +
                ", vestingDate=" + vestingDate +
                ", quantity=" + quantity +
                '}';
    }
}
