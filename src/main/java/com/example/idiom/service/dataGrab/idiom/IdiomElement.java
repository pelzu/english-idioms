package com.example.idiom.service.dataGrab.idiom;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class IdiomElement {
    public static final String IDIOM_LINK = "https://www.ang.pl/slownictwo/idiomy/page/";
    private final IdiomPagination idiomPagination;

    public IdiomElement(IdiomPagination idiomPagination) {
        this.idiomPagination = idiomPagination;
    }

    public Elements getElements() {
        int idiomNumbers = this.idiomPagination.getNumberOfPageIdiom();
        ExecutorService executor = Executors.newFixedThreadPool(idiomNumbers);
        List<Future<Elements>> elementFutures = new ArrayList<>();
        Elements elements = new Elements();

        for (int i = 1; i <= idiomNumbers; i++) {
            int increment = i;


            Callable<Elements> task = () -> {
                try {
                    return Jsoup.connect(IDIOM_LINK + increment).get().select("div[style*=border-bottom: 1px solid #ccc;]");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            elementFutures.add(executor.submit(task));

        }

        for (Future<Elements> elementFuture : elementFutures) {
            try {
                elements.addAll(elementFuture.get());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
        return elements;
    }
}
