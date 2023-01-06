package com.example.idiom.service.dataGrab.idiom;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
public class IdiomElement {
    public static final String IDIOM_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";
    private final IdiomPagination idiomPagination;


    public IdiomElement(IdiomPagination idiomPagination) {
        this.idiomPagination = idiomPagination;
    }


    public Elements getElements() {
        int idiomNumbers = this.idiomPagination.getNumberOfPageIdiom();
        Elements elements = new Elements();

        java.util.concurrent.Executor executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 1; i <= idiomNumbers; i++) {
            int increment = i;
            java.util.concurrent.CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    elements.addAll(Jsoup.connect(IDIOM_LINK + increment).get().select("div[style*=border-bottom: 1px solid #ccc;]"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, executor);
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return elements;
    }


}
