package com.example.idiom.service.phrasal;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class PhrasalElement {

    private final String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";

    private final PhrasalPagination phrasalPagination;

    public PhrasalElement(PhrasalPagination phrasalPagination) {
        this.phrasalPagination = phrasalPagination;
    }

    public Elements getElements() {

        int paginationNumbers = this.phrasalPagination.getNumberOfPagePhrasalVerb();
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        Elements elements = new Elements();
        List<Future<Elements>> futureElements = new ArrayList<>();

        for (int i = 1; i <= paginationNumbers; i++) {
            int increment = i;
            Callable<Elements> task = () -> {
                try {
                    log.info("Downloading HTML site:" + PHRASAL_VERB_LINK + increment);
                    return Jsoup.connect(PHRASAL_VERB_LINK + increment).get().select("div[style*=border-bottom: 1px solid #ccc;]");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            futureElements.add(executorService.submit(task));
        }
        for (Future<Elements> element : futureElements
        ) {
            try {
                elements.addAll(element.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        executorService.shutdown();
        return elements;
    }


}


