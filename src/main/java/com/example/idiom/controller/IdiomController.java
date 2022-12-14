package com.example.idiom.controller;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.model.Idiom;
import com.example.idiom.oldAproach.IdiomPageService;
import com.example.idiom.oldAproach.IdiomService;
import com.example.idiom.service.dataGrab.ChooseClass;
import com.example.idiom.service.dataGrab.DefaultImplAngPl;
import com.example.idiom.service.dataGrab.idiom.IdiomAudioGrabber;
import com.example.idiom.service.dataGrab.idiom.IdiomCsVConverter;
import com.example.idiom.service.dataGrab.idiom.IdiomImpl;
import com.example.idiom.service.dataGrab.phrasal.PhrasalVerbsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IdiomController {

    private final IdiomService idiomService;
    private final IdiomCsVConverter idiomCsVConverter;

    private final IdiomPageService idiomPageService;

    private final IdiomAudioGrabber idiomAudioGrabber;


    private final PhrasalVerbsImpl phrasalVerbsImpl;

    private final IdiomImpl idiomImpl;

    private final DefaultImplAngPl defaultImplAngPl;

    private final ChooseClass chooseClass;
    private final int idiomLength = 520;
    private final ArrayList<Idiom> idiomsArrayList = new ArrayList<>();

    @Autowired
    public IdiomController(IdiomService idiomService, IdiomCsVConverter idiomCsVConverter, IdiomPageService idiomPageService, IdiomAudioGrabber idiomAudioGrabber, PhrasalVerbsImpl phrasalVerbsImpl, IdiomImpl idiomImpl, DefaultImplAngPl defaultImplAngPl, ChooseClass chooseClass) {
        this.idiomService = idiomService;
        this.idiomCsVConverter = idiomCsVConverter;
        this.idiomPageService = idiomPageService;
        this.idiomAudioGrabber = idiomAudioGrabber;
        this.phrasalVerbsImpl = phrasalVerbsImpl;
        this.idiomImpl = idiomImpl;
        this.defaultImplAngPl = defaultImplAngPl;
        this.chooseClass = chooseClass;
    }

    @GetMapping("/learn")
    public List<DataGrabberAngPl> getPhrasal(@RequestParam(required = false) String kind, @RequestParam(required = false) String audio, @RequestParam(required = false) String csv) {

        return chooseClass.getLists(kind).getObject(audio, csv);

    }


    //Old approach

    @GetMapping("/idiomsPagination")
    public void getIdiomsByPage() throws IOException {
        for (int i = 1; i <= 11; i++) {
            System.out.println("Page " + i);
            idiomPageService.getOnePageOfIdioms(i);
        }

        idiomPageService.getArraySize();
    }

    @GetMapping("/idiomsOneGet")
    public void getIdiomsbyOneGet() throws IOException {

        for (int i = 0; i <= idiomLength; i++) {  //

            addAllIdioms(i);
        }
        writeToCSV();
    }


    public void writeToCSV() {
        idiomCsVConverter.save(idiomsArrayList);
    }

    public void addAllIdioms(int iterator) {
        idiomsArrayList.add(idiomService.getOneIdiom(iterator));

    }


}
