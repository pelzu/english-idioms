package com.example.idiom.service.phrasal;

import com.example.idiom.service.approach.HtmlPaginationScrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhrasalPagination {
    private final String PHRASAL_VERB_BASE_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/1";

    public int getNumberOfPagePhrasalVerb() {
        return   HtmlPaginationScrapper.getNumberOfPage(PHRASAL_VERB_BASE_LINK);



    }
}
