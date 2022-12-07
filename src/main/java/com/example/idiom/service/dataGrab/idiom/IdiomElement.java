package com.example.idiom.service.dataGrab.idiom;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IdiomElement {
    public static final String IDIOM_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";
    private final IdiomPagination idiomPagination;

    @Autowired
    public IdiomElement(IdiomPagination idiomPagination) {
        this.idiomPagination = idiomPagination;
    }


    public Elements getElements() {
        try {
            int idiomNumbers = this.idiomPagination.getNumberOfPageIdiom();
            Elements elements = new Elements();
            for (int i = 1; i <= idiomNumbers; i++) {
                elements.addAll(Jsoup.connect(IDIOM_LINK + i).get().select("div[style*=border-bottom: 1px solid #ccc;]"));
            }
            return elements;

        } catch (IOException e) {
            throw new RuntimeException(e);
            //TODO Add hand RuntimeException(e) handling
        }
    }
}
