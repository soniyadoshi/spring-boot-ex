package com.carta.comparators;

import com.carta.model.EmpTransaction;

import java.util.Comparator;

public class SortByEmployeeId implements Comparator<EmpTransaction> {

    @Override
    public int compare(EmpTransaction o1, EmpTransaction o2) {

        return o1.getEmployeeId().compareToIgnoreCase(o2.getEmployeeId());
    }
}
