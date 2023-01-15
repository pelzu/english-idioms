package com.example.idiom.model.idiom;

import java.util.Comparator;

public class IdiomIdComparator implements Comparator<Idiom> {
    @Override
    public int compare(Idiom o1, Idiom o2) {
        return (int) (o1.getId() - o2.getId());
    }
}
