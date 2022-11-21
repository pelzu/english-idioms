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

    private int idiomLength = 2;

    private ArrayList<IdiomModel> idiomsArrayList = new ArrayList<>();

@Autowired
    public IdiomController(IdiomService idiomService, CsVWriter csVWriter, AudioService audioService) {
        this.idiomService = idiomService;
        this.csVWriter = csVWriter;
        this.audioService = audioService;
    }

    @GetMapping("/idiom")
    public void getEnglishMeaning() {
        idiomsArrayList.ensureCapacity(idiomLength);
        for (int i = 1; i <= idiomLength; i++) {
            idiomsArrayList.add(idiomService.getOneIdiom(i));
        }
        csVWriter.save(idiomsArrayList);
    }
    @GetMapping("/1")
    public void download() throws IOException {

    audioService.downLoadAudio();

    }


}
