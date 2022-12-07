package com.example.idiom.controller;

import com.example.idiom.service.dataGrab.idiom.IdiomCsVConverter;
import com.example.idiom.service.dataGrab.idiom.IdiomImpl;
import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.model.Idiom;
import com.example.idiom.service.*;
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

    private final AudioService audioService;


    private final PhrasalVerbsImpl phrasalVerbsImpl;

    private final IdiomImpl idiomImpl ;


    private final int idiomLength = 520;
    private final ArrayList<Idiom> idiomsArrayList = new ArrayList<>();

    @Autowired
    public IdiomController(IdiomService idiomService, IdiomCsVConverter idiomCsVConverter, IdiomPageService idiomPageService, AudioService audioService, PhrasalVerbsImpl phrasalVerbsImpl, IdiomImpl idiomImpl) {
        this.idiomService = idiomService;
        this.idiomCsVConverter = idiomCsVConverter;
        this.idiomPageService = idiomPageService;
        this.audioService = audioService;
        this.phrasalVerbsImpl = phrasalVerbsImpl;
        this.idiomImpl = idiomImpl;
    }

    @GetMapping("/learn")
    public List<DataGrabberAngPl> getPhrasal(@RequestParam String kind) {
        List<DataGrabberAngPl>dataGrabberAngPls=getRightObjectByParam(kind).getObject();


        return dataGrabberAngPls;

    }



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
            downloadAudio(i);
        }
        writeToCSV();
    }

    public void downloadAudio(int iterator) {
        audioService.downLoadAudio(idiomsArrayList.get(iterator));
    }

    public void writeToCSV() {
        idiomCsVConverter.save(idiomsArrayList);
    }

    public void addAllIdioms(int iterator) {
        idiomsArrayList.add(idiomService.getOneIdiom(iterator));

    }

    public DataGrabberAngPl getRightObjectByParam(String param) {
        if (param.equals("idiom")) {
            return idiomImpl ;
        } else if (param.equals("phrasal")) {
            return phrasalVerbsImpl ;
        }else return null ;

    }


}
