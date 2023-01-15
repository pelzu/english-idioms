package com.example.idiom.controller;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.service.dataGrab.ChooseClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class IdiomController {

    private final ChooseClass chooseClass;


    public IdiomController(ChooseClass chooseClass) {
        this.chooseClass = chooseClass;
    }

    @GetMapping("/learn")
    public List<DataGrabberAngPl> getPhrase(@RequestParam(required = false) String kind,
                                            @RequestParam(required = false) String audio,
                                            @RequestParam(required = false) String csv) {
        return chooseClass.getRightImpl(kind).getObject(audio, csv);
    }
}
