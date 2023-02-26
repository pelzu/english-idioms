package com.example.idiom.service;

import com.example.idiom.service.idiom.IdiomImpl;
import com.example.idiom.service.nooption.DefaultImplAngPl;
import com.example.idiom.service.phrasal.PhrasalVerbsImpl;

import java.util.HashMap;


public class ChooseClass {

    private final HashMap<String, DataGrabberAngPl> angPlHashMap = new HashMap<String, DataGrabberAngPl>();

    private final PhrasalVerbsImpl phrasalVerbs;
    private final IdiomImpl idiomImpl;
    private final DefaultImplAngPl defaultImplAngPl;

    public ChooseClass(PhrasalVerbsImpl phrasalVerbs, IdiomImpl idiom, DefaultImplAngPl defaultImplAngPl) {
        this.phrasalVerbs = phrasalVerbs;
        this.idiomImpl = idiom;
        this.defaultImplAngPl = defaultImplAngPl;
    }

    public DataGrabberAngPl getImplByParameter(String kind) {
        saveHashMap();
        if (angPlHashMap.containsKey(kind)) {
            return angPlHashMap.get(kind);
        } else return defaultImplAngPl;

    }

    public void saveHashMap() {
        angPlHashMap.put("idiom", idiomImpl);
        angPlHashMap.put("phrasal", phrasalVerbs);

    }


}
