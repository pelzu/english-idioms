package com.example.idiom.controller;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.example.idiom.repository.idiom.IdiomDbImpl;
import com.example.idiom.repository.phrasal.PhrasalVerbDbImpl;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import com.example.idiom.service.ImplSelector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TranslateController {

    private final ImplSelector implSelector;
    private final IdiomDbImpl idiomDbImpl;

    private final PhrasalVerbDbImpl phrasalVerbDbImpl;

    public TranslateController(ImplSelector implSelector, IdiomDbImpl idiomDbImpl, PhrasalVerbDbImpl phrasalVerbDbImpl) {
        this.implSelector = implSelector;
        this.idiomDbImpl = idiomDbImpl;
        this.phrasalVerbDbImpl = phrasalVerbDbImpl;

    }

    @GetMapping("/learn")
    public List<IdiomAndPhrasalInterface> getPhraseByParams(@RequestParam(required = false) String kind,
                                                            @RequestParam(required = false) String audio,
                                                            @RequestParam(required = false) String csv) {

        return implSelector.getImplByParameter(kind).getIdiomOrPhrasalList(audio, csv);
    }

    public List<Idiom> getIdioms() {
        return implSelector.getImplByParameter("idiom").getIdiomOrPhrasalList("false", "false");
    }

    public List<Idiom> getIdiomsFromDB() {
        return idiomDbImpl.getIdioms();
    }

    public List<Idiom> grabIdiomsToDB() {
        List<Idiom> idioms = getIdioms();
        idiomDbImpl.saveIdioms(idioms);
        return idioms;
    }

    public List<PhrasalVerb> downloadPhrasalVerbs() {

        return implSelector.getImplByParameter("phrasal").getIdiomOrPhrasalList("false", "false");
    }

    public List<PhrasalVerb> getPhrasalsFromDB() {
        return phrasalVerbDbImpl.getPhrasalVerbs();
    }

    public List<PhrasalVerb> grabPhrasalsToDb() {
        List<PhrasalVerb> phrasals = downloadPhrasalVerbs();
        phrasalVerbDbImpl.savePhrasalVerbs(phrasals);
        return phrasals;
    }



}
