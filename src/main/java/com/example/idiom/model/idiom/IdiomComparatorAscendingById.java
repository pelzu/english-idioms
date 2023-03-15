package com.example.idiom.model.idiom;

import java.util.Comparator;

public class IdiomComparatorAscendingById implements Comparator<Idiom> {
    @Override
    public int compare(Idiom o1, Idiom o2) {
        int difference=o1.getId().compareTo(o2.getId());
        return difference;
    }
}
