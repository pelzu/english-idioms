package com.example.idiom.service.dataGrab;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.service.dataGrab.idiom.IdiomImpl;
import com.example.idiom.service.dataGrab.phrasal.PhrasalVerbsImpl;


public class ChooseClass {

    private final PhrasalVerbsImpl phrasalVerbs;
    private final IdiomImpl idiom;
    private final DefaultImplAngPl defaultImplAngPl;

    public ChooseClass(PhrasalVerbsImpl phrasalVerbs, IdiomImpl idiom, DefaultImplAngPl defaultImplAngPl) {
        this.phrasalVerbs = phrasalVerbs;
        this.idiom = idiom;
        this.defaultImplAngPl = defaultImplAngPl;
    }

    public DataGrabberAngPl getRightImpl(String kind) {

        if (kind == null) {
            return defaultImplAngPl;
        } else if (kind.equals("idiom")) {
            return idiom;
        } else if (kind.equals("phrasal")) {
            return phrasalVerbs;
        } else
            return defaultImplAngPl;
    }


}
