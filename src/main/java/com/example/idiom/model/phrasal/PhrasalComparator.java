package com.example.idiom.model.phrasal;

import java.util.Comparator;

public class PhrasalComparator implements Comparator<PhrasalVerb> {
    @Override
    public int compare(PhrasalVerb o1, PhrasalVerb o2) {
        return Integer.parseInt(o1.getId())-Integer.parseInt(o2.getId());
    }
}
