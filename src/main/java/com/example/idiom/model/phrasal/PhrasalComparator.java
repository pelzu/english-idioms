package com.example.idiom.model.phrasal;

import java.util.Comparator;

public class PhrasalComparator implements Comparator<PhrasalVerb> {
    @Override
    public int compare(PhrasalVerb o1, PhrasalVerb o2) {
        return (int) (o1.getId() - o2.getId());
    }
}
