package com.example.idiom.service.idiom;

import com.example.idiom.service.approach.pagination.PaginationScrapper;
import com.example.idiom.service.approach.pagination.PaginationInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdiomPagination implements PaginationInterface {

    private final String IDIOM_BASE_LINK = "https://www.ang.pl/slownictwo/idiomy/page/1";


    @Override
    public int getNumberOfPage() {
        return PaginationScrapper.getNumberOfPage(IDIOM_BASE_LINK);
    }
}

