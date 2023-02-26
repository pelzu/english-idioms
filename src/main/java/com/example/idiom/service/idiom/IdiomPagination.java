package com.example.idiom.service.idiom;

import com.example.idiom.service.approach.HtmlPaginationScrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdiomPagination {

    private final String IDIOM_BASE_LINK = "https://www.ang.pl/slownictwo/idiomy/page/1";
    public int getNumberOfPageIdiom() {
        return   HtmlPaginationScrapper.getNumberOfPage(IDIOM_BASE_LINK);
    }

}

