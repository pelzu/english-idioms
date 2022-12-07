package com.example.idiom.service.dataGrab.phrasal;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class PhrasalElement {

    private final String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";

    private final PhrasalPagination phrasalPagination;

    @Autowired
    public PhrasalElement(PhrasalPagination phrasalPagination) {
        this.phrasalPagination = phrasalPagination;
    }

    public Elements getElements() {
        try {
            int paginationNumbers = this.phrasalPagination.getNumberOfPagePhrasalVerb();
            Elements elements = new Elements();
            for (int i = 1; i <= paginationNumbers; i++) {
                elements.addAll(Jsoup.connect(PHRASAL_VERB_LINK + i).get().select("div[style*=border-bottom: 1px solid #ccc;]"));
            }
            return elements;

        } catch (IOException e) {
            throw new RuntimeException(e);
            //TODO Add hand RuntimeException(e) handling

        }
    }


}


