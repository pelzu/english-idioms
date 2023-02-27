package com.example.idiom.service.idiom;

import com.example.idiom.service.approach.elements.ElementScrapper;
import com.example.idiom.service.approach.elements.ElementScrapperInterface;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;

@Slf4j
public class IdiomElement implements ElementScrapperInterface {
    public static final String IDIOM_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";
    private final IdiomPagination idiomPagination;

    public IdiomElement(IdiomPagination idiomPagination) {
        this.idiomPagination = idiomPagination;
    }

    @Override
    public Elements getElements() {

        return ElementScrapper.getElements(IDIOM_LINK, idiomPagination);
    }


}
