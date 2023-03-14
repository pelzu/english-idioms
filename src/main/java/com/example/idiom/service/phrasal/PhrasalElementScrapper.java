package com.example.idiom.service.phrasal;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class PhrasalElementScrapper {

    private final String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";


    public Elements getPhrasalElements() {

        int numberOfPagePhrasalVerb = getNumberOfPagePhrasalVerb();
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        Elements elements = new Elements();
        List<Future<Elements>> futureElements = new ArrayList<>();

        for (int i = 1; i <= numberOfPagePhrasalVerb; i++) {
            int increment = i;
            Callable<Elements> task = () -> {
                try {
                    log.info("Downloading HTML site:" + PHRASAL_VERB_LINK + increment);
                    return Jsoup.connect(PHRASAL_VERB_LINK + increment).get().select("div[class=row border-bottom py-2]");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            futureElements.add(executorService.submit(task));
        }
        for (Future<Elements> element : futureElements) {
            try {
                elements.addAll(element.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        executorService.shutdown();
        return elements;
    }

    public int getNumberOfPagePhrasalVerb() {
        try {
            log.info("Downloading number of pagination:" + PHRASAL_VERB_LINK);
            Document tempDoc = null;
            tempDoc = Jsoup.connect(PHRASAL_VERB_LINK).get();
            Elements elements = tempDoc.getElementsByClass("pagination");
            String numberOfPage = elements.first().lastElementChild().text();
            log.info("Number of pagination is :" + numberOfPage);
            return Integer.parseInt(numberOfPage);

        } catch (IOException e) {
            throw new RuntimeException(e);
            //TODO Add hand RuntimeException(e) handling
        }
    }


}


