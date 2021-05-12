package com.carta.comparators;

import com.carta.model.EmpTransaction;

import java.util.Comparator;

public class SortByAwardId implements Comparator<EmpTransaction> {

    @Override
    public int compare(EmpTransaction o1, EmpTransaction o2) {
        return o1.getAwardId().compareToIgnoreCase(o2.getAwardId());
    }
}
