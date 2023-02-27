package com.example.idiom.service.phrasal;

import com.example.idiom.service.approach.pagination.PaginationScrapper;
import com.example.idiom.service.approach.pagination.PaginationInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhrasalPagination implements PaginationInterface {
    private final String PHRASAL_VERB_BASE_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/1";

    @Override
    public int getNumberOfPage() {
        return  PaginationScrapper.getNumberOfPage(PHRASAL_VERB_BASE_LINK);
    }
}
