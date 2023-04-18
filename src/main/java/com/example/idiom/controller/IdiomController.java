package com.example.idiom.controller;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.example.idiom.repository.idiom.IdiomDbService;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import com.example.idiom.service.ImplSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class IdiomController {

    private final ImplSelector implSelector;
    private final IdiomDbService idiomDbService;


    public IdiomController(ImplSelector implSelector, IdiomDbService idiomDbService) {
        this.implSelector = implSelector;
        this.idiomDbService = idiomDbService;
    }

    @GetMapping("/learn")
    public List<IdiomAndPhrasalInterface> getPhraseByParams(@RequestParam(required = false) String kind,
                                                            @RequestParam(required = false) String audio,
                                                            @RequestParam(required = false) String csv) {

        return implSelector.getImplByParameter(kind).getIdiomOrPhrasalList(audio, csv);
    }

    public List<Idiom> downloadIdioms() {
        return implSelector.getImplByParameter("idiom").getIdiomOrPhrasalList("false", "false");
    }

    public List<PhrasalVerb> downloadPhrasalVerbs() {

        return implSelector.getImplByParameter("idiom").getIdiomOrPhrasalList("false", "false");
    }

    public List<Idiom> getIdiomsFromDB() {
        return idiomDbService.getIdioms();
    }

    public List<Idiom> addIdiomsToDB() {
        List<Idiom> idioms = downloadIdioms();
        idiomDbService.saveIdioms(idioms);
        return idioms;
    }

}
