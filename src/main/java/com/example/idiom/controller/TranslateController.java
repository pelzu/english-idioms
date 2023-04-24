package com.example.idiom.controller;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.example.idiom.repository.idiom.IdiomDbService;
import com.example.idiom.repository.phrasal.PhrasalVerbDbService;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import com.example.idiom.service.ImplSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class TranslateController {

    private final ImplSelector implSelector;
    private final IdiomDbService idiomDbService;

    private final PhrasalVerbDbService phrasalVerbDbService;

    public TranslateController(ImplSelector implSelector, IdiomDbService idiomDbService, PhrasalVerbDbService phrasalVerbDbService) {
        this.implSelector = implSelector;
        this.idiomDbService = idiomDbService;
        this.phrasalVerbDbService = phrasalVerbDbService;

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
        return idiomDbService.getIdioms();
    }

    public List<Idiom> grabIdiomsToDB() {
        List<Idiom> idioms = getIdioms();
        idiomDbService.saveIdioms(idioms);
        return idioms;
    }

    public List<PhrasalVerb> downloadPhrasalVerbs() {

        return implSelector.getImplByParameter("phrasal").getIdiomOrPhrasalList("false", "false");
    }

    public List<PhrasalVerb> getPhrasalsFromDB() {
        return phrasalVerbDbService.getPhrasalVerbs();
    }

    public List<PhrasalVerb> grabPhrasalsToDb() {
        List<PhrasalVerb> phrasals = downloadPhrasalVerbs();
        phrasalVerbDbService.savePhrasalVerbs(phrasals);
        return phrasals;
    }


}
