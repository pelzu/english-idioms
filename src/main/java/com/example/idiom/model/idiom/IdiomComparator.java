package com.example.idiom.model.idiom;

import java.util.Comparator;

public class IdiomComparator implements Comparator<Idiom> {
    @Override
    public int compare(Idiom o1, Idiom o2) {
        return (o1.getId().intValue() - o2.getId().intValue());
    }
}
