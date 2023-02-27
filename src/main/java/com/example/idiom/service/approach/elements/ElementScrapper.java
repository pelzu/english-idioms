package com.example.idiom.service.approach.elements;

import com.example.idiom.service.approach.pagination.PaginationInterface;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ElementScrapper {


    public static Elements getElements(String link, PaginationInterface paginationInterface) {
        int idiomNumbers = paginationInterface.getNumberOfPage();
        ExecutorService executor = Executors.newFixedThreadPool(30);
        List<Future<Elements>> elementFutures = new ArrayList<>();
        Elements elements = new Elements();

        for (int i = 1; i <= idiomNumbers; i++) {
            int increment = i;
            Callable<Elements> task = () -> {
                try {
                    log.info("Downloading HTML site:" + link + increment);
                    return Jsoup.connect(link + increment).get().select("div[class=row border-bottom py-2]");
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
