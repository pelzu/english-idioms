package com.example.idiom.service.dataGrab.idiom;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class IdiomElement {
    public static final String IDIOM_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";
    private final IdiomPagination idiomPagination;

    @Autowired
    public IdiomElement(IdiomPagination idiomPagination) {
        this.idiomPagination = idiomPagination;
    }


    public Elements getElements() {
        int idiomNumbers = this.idiomPagination.getNumberOfPageIdiom();
        Elements elements = new Elements();
        for (int i = 1; i <= idiomNumbers; i++) {

            int increment = i;
            Runnable r = () -> {
                try {
                    elements.addAll(Jsoup.connect(IDIOM_LINK + increment).get().select("div[style*=border-bottom: 1px solid #ccc;]"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            var t = new Thread(r);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return elements;

    }
}
