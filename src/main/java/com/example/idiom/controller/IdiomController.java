package com.example.idiom.controller;

import com.example.idiom.service.ImplSelector;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class IdiomController {

    private final ImplSelector implSelector;


    public IdiomController(ImplSelector implSelector) {
        this.implSelector = implSelector;
    }

    @GetMapping("/learn")
    public List<IdiomAndPhrasalInterface> getPhraseByParams(@RequestParam(required = false) String kind,
                                                            @RequestParam(required = false) String audio,
                                                            @RequestParam(required = false) String csv) {

        return implSelector.getImplByParameter(kind).getIdiomOrPhrasalList(audio, csv);
    }

}
