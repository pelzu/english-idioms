package com.example.idiom.service;

import com.example.idiom.service.idiom.IdiomService;
import com.example.idiom.service.nooption.DefaultImplAngPl;
import com.example.idiom.service.phrasal.PhrasalVerbService;

import javax.annotation.PostConstruct;
import java.util.HashMap;


public class ImplSelector {

    private final HashMap<String, IdiomAndPhrasalInterface> angPlHashMap = new HashMap<>();

    private final PhrasalVerbService phrasalVerbs;
    private final IdiomService idiomService;
    private final DefaultImplAngPl defaultImplAngPl;

    public ImplSelector(PhrasalVerbService phrasalVerbs, IdiomService idiom, DefaultImplAngPl defaultImplAngPl) {
        this.phrasalVerbs = phrasalVerbs;
        this.idiomService = idiom;
        this.defaultImplAngPl = defaultImplAngPl;
    }


    public IdiomAndPhrasalInterface getImplByParameter(String kind) {
        return angPlHashMap.getOrDefault(kind, defaultImplAngPl);

    }

    @PostConstruct
    public void saveHashMap() {
        angPlHashMap.put("idiom", idiomService);
        angPlHashMap.put("phrasal", phrasalVerbs);

    }


}
