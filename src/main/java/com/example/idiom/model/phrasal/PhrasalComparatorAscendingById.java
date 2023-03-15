package com.example.idiom.model.phrasal;

import java.util.Comparator;

public class PhrasalComparatorAscendingById implements Comparator<PhrasalVerb> {
    @Override
    public int compare(PhrasalVerb o1, PhrasalVerb o2) {
        int difference=o1.getId().compareTo(o2.getId());
        return difference ;
    }
}
