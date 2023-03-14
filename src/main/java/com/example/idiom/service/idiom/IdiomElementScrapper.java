package com.example.idiom.service.idiom;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class IdiomElementScrapper {

    public Elements getElementsContainingIdioms() {
        String idiomBaseLink = "https://www.ang.pl/slownictwo/idiomy/page/";
        int numberOfPages = getNumberOfPageIdiom();

        ExecutorService executor = Executors.newFixedThreadPool(30);
        List<Future<Elements>> elementFutures = new ArrayList<>();
        Elements rawIdiomsInElements = new Elements();

        for (int i = 1; i <= numberOfPages; i++) {
            int increment = i;
            Callable<Elements> task = () -> {
                try {
                    log.info("Downloading HTML site:" + idiomBaseLink + increment);
                    return Jsoup.connect(idiomBaseLink + increment).get().select("div[class=row border-bottom py-2]");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            elementFutures.add(executor.submit(task));
        }

        for (Future<Elements> elementFuture : elementFutures) {
            try {
                rawIdiomsInElements.addAll(elementFuture.get());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
        return rawIdiomsInElements;
    }

    public int getNumberOfPageIdiom() {
        try {
            String idiomFirstPageLink = "https://www.ang.pl/slownictwo/idiomy/page/1";
            log.info("Downloading number of pagination:" + idiomFirstPageLink);
            Document tempDoc = null;
            tempDoc = Jsoup.connect(idiomFirstPageLink).get();
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
