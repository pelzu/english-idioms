package com.example.idiom.controller;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.oldAproach.IdiomPageService;
import com.example.idiom.oldAproach.IdiomService;
import com.example.idiom.service.dataGrab.ChooseClass;
import com.example.idiom.service.dataGrab.idiom.IdiomCsVConverter;
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
    private final ChooseClass chooseClass;
    private final ArrayList<Idiom> idiomsArrayList = new ArrayList<>();

    @Autowired
    public IdiomController(IdiomService idiomService, IdiomCsVConverter idiomCsVConverter, IdiomPageService idiomPageService, ChooseClass chooseClass) {
        this.idiomService = idiomService;
        this.idiomCsVConverter = idiomCsVConverter;
        this.idiomPageService = idiomPageService;
        this.chooseClass = chooseClass;
    }


    @GetMapping("/learn")
    public List<DataGrabberAngPl> getPhrasal(@RequestParam(required = false) String kind, @RequestParam(required = false) String audio, @RequestParam(required = false) String csv) {
        return chooseClass.getRightImpl(kind).getObject(audio, csv);
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
    public void getIdiomsbyOneGet()  {
        int idiomLength = 520;
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
