package com.example.idiom.controller;

import com.example.idiom.model.IdiomModel;
import com.example.idiom.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class IdiomController {

    private final IdiomService idiomService;
    private final CsVWriter csVWriter;

    private final IdiomPageService idiomPageService;

    private final AudioService audioService;

    private final IdiomJsoupApproach idiomJsoupApproach;

    private final int idiomLength = 520;
    private final ArrayList<IdiomModel> idiomsArrayList = new ArrayList<>();


    @Autowired
    public IdiomController(IdiomService idiomService, CsVWriter csVWriter, IdiomPageService idiomPageService, AudioService audioService, IdiomJsoupApproach idiomJsoupApproach) {
        this.idiomService = idiomService;
        this.csVWriter = csVWriter;
        this.idiomPageService = idiomPageService;
        this.audioService = audioService;
        this.idiomJsoupApproach = idiomJsoupApproach;
    }


    @GetMapping("/idiomJsoup")
    public void getIdiomsByJsoup() throws IOException {
        idiomJsoupApproach.getIdiom();


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
        csVWriter.save(idiomsArrayList);
    }

    public void addAllIdioms(int iterator) {
        idiomsArrayList.add(idiomService.getOneIdiom(iterator));

    }


}
