package com.example.idiom.controller;

import com.example.idiom.model.IdiomModel;
import com.example.idiom.service.AudioService;
import com.example.idiom.service.CsVWriter;
import com.example.idiom.service.IdiomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class IdiomController {

    private IdiomService idiomService;
    private CsVWriter csVWriter;

    private AudioService audioService;

    private int idiomLength = 100;

    private ArrayList<IdiomModel> idiomsArrayList = new ArrayList<>();

@Autowired
    public IdiomController(IdiomService idiomService, CsVWriter csVWriter, AudioService audioService) {
        this.idiomService = idiomService;
        this.csVWriter = csVWriter;
        this.audioService = audioService;
    }

    @GetMapping("/idioms")
    public void getEnglishMeaning() throws IOException {

        for (int i = 0; i <= idiomLength; i++) {
            addAllIdioms(i);
            downloadAudio(i);
        }
        writeToCSV();
    }

    public void downloadAudio(int iterator)  {
        audioService.downLoadAudio(idiomsArrayList.get(iterator));
    }

    public void writeToCSV() {
        csVWriter.save(idiomsArrayList);
    }
    public void addAllIdioms(int iterator) {
        idiomsArrayList.add(idiomService.getOneIdiom(iterator));

    }




}
