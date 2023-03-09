package com.example.idiom.service;

import com.example.idiom.service.idiom.IdiomImpl;
import com.example.idiom.service.nooption.DefaultImplAngPl;
import com.example.idiom.service.phrasal.PhrasalVerbsImpl;

import javax.annotation.PostConstruct;
import java.util.HashMap;


public class ImpSelector {

    private final HashMap<String, IdiomAndPhrasalInterface> angPlHashMap = new HashMap<String, IdiomAndPhrasalInterface>();

    private final PhrasalVerbsImpl phrasalVerbs;
    private final IdiomImpl idiomImpl;
    private final DefaultImplAngPl defaultImplAngPl;

    public ImpSelector(PhrasalVerbsImpl phrasalVerbs, IdiomImpl idiom, DefaultImplAngPl defaultImplAngPl) {
        this.phrasalVerbs = phrasalVerbs;
        this.idiomImpl = idiom;
        this.defaultImplAngPl = defaultImplAngPl;
    }


    public IdiomAndPhrasalInterface getImplByParameter(String kind) {
        return angPlHashMap.getOrDefault(kind, defaultImplAngPl);

    }

    @PostConstruct
    public void saveHashMap() {
        angPlHashMap.put("idiom", idiomImpl);
        angPlHashMap.put("phrasal", phrasalVerbs);

    }


}
