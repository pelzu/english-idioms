package com.example.idiom.service.phrasal;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.idiom.inter.DataGrabberAngPl.PHRASAL_VERB_LINK;

@Service
public class PhrasalElement {

    private final PhrasalPagination phrasalPagination;

    @Autowired
    public PhrasalElement(PhrasalPagination phrasalPagination) {
        this.phrasalPagination = phrasalPagination;
    }

    public Elements getElements() throws IOException {
        int paginationNumbers = this.phrasalPagination.getNumberOfPagePhrasalVerb();
        Elements elements = new Elements();
        for (int i = 1; i <= paginationNumbers; i++) {
            elements.addAll(Jsoup.connect(PHRASAL_VERB_LINK + i).get().select("div[style*=border-bottom: 1px solid #ccc;]"));
        }
        return elements;
    }
}
