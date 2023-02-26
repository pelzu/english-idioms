package com.example.idiom.service;

import com.example.idiom.service.idiom.IdiomImpl;
import com.example.idiom.service.nooption.DefaultImplAngPl;
import com.example.idiom.service.phrasal.PhrasalVerbsImpl;


public class ChooseClass {

    private final PhrasalVerbsImpl phrasalVerbs;
    private final IdiomImpl idiomImpl;
    private final DefaultImplAngPl defaultImplAngPl;

    public ChooseClass(PhrasalVerbsImpl phrasalVerbs, IdiomImpl idiom, DefaultImplAngPl defaultImplAngPl) {
        this.phrasalVerbs = phrasalVerbs;
        this.idiomImpl = idiom;
        this.defaultImplAngPl = defaultImplAngPl;
    }

    public DataGrabberAngPl getImplByParameter(String kind) {

        if (kind == null) {
            return defaultImplAngPl;
        } else if (idiomImpl.test(kind)) {
            return idiomImpl;
        } else if (phrasalVerbs.test(kind)) {
            return phrasalVerbs;
        } else
            return defaultImplAngPl;
    }


}
