package com.example.idiom.model.phrasal;

import java.util.Comparator;

public class PhrasalComparatorById implements Comparator<PhrasalVerb> {
    @Override
    public int compare(PhrasalVerb o1, PhrasalVerb o2) {
        int compareDifference=o1.getId().compareTo(o2.getId());
        return compareDifference ;
    }
}
