package com.example.idiom.controller;

import com.example.idiom.service.ImpSelector;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class IdiomController {

    private final ImpSelector impSelector;


    public IdiomController(ImpSelector impSelector) {
        this.impSelector = impSelector;
    }

    @GetMapping("/learn")
    public List<IdiomAndPhrasalInterface> getPhrase(@RequestParam(required = false) String kind,
                                                    @RequestParam(required = false) String audio,
                                                    @RequestParam(required = false) String csv) {
        return impSelector.getImplByParameter(kind).getIdiomOrPhrasalList(audio, csv);
    }
}
