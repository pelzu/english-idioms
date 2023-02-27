package com.example.idiom.service.phrasal;

import com.example.idiom.service.approach.elements.ElementScrapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;

@Slf4j
public class PhrasalElement {

    private final String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";

    private final PhrasalPagination phrasalPagination;

    public PhrasalElement(PhrasalPagination phrasalPagination) {
        this.phrasalPagination = phrasalPagination;
    }

    public Elements getPhrasalElements() {

        return ElementScrapper.getElements(PHRASAL_VERB_LINK, phrasalPagination);
    }


}


