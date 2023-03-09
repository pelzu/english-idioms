package com.example.idiom.model.idiom;

import java.util.Comparator;

public class IdiomComparatorById implements Comparator<Idiom> {
    @Override
    public int compare(Idiom o1, Idiom o2) {
        int compareDifference=o1.getId().compareTo(o2.getId());
        return compareDifference;
    }
}
