package com.example.idiom.service.phrasal;

import com.example.idiom.model.phrasal.PhrasalComparatorById;
import com.example.idiom.model.phrasal.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PhrasalVerbsParser {

    private final String PREFIX_LINK = "https://www.ang.pl";
    private static int  counter=0 ;

    public List<PhrasalVerb> parseToPhrasalVerbs(Elements elements) {
        List<PhrasalVerb> phrasalVerbList = new ArrayList<>();

        elements.forEach(element -> {
            PhrasalVerb phrasalVerb = PhrasalVerb.builder()
                    .id(Long.valueOf(getId()))
                    .englishMeaning(getEnglishMeaning(element))
                    .polishMeaning(getPolishTranslation(element))
                    .englishExample(getExampleEnglish(element))
                    .linkToPhrasalVerb(getLinkToPhrasalVerb(element)).
                    build();

            phrasalVerbList.add(phrasalVerb);
        });
        phrasalVerbList.sort(new PhrasalComparatorById());

        log.info("Parsed and added " + phrasalVerbList.size() + " number of Phrasal to list");
        return phrasalVerbList;
    }

    public String getPolishTranslation(Element el) {
        Node polishTranslation = el.select("div[class=col-7 col-sm-4 lh-1]").first().firstChild();

        return polishTranslation.toString();
    }

    public String getExampleEnglish(Element el) {
        StringBuilder exampleEnglish = new StringBuilder();
        List<Node> exampleEnglishNodes = el.select("p[class=mb-1]").first().childNodes();

        for (Node node : exampleEnglishNodes) {
            if (node.childNodes().isEmpty()) {
                exampleEnglish.append(node);
            } else exampleEnglish.append(node.firstChild());
        }
        return exampleEnglish.toString();
    }

    public String getEnglishMeaning(Element el) {
        Node englishTranslation = el.select("div[class=col-5 col-sm-3 ang]").first().firstChild().firstChild();
        return englishTranslation.toString();
    }

    public String getLinkToPhrasalVerb(Element el) {
        String linkToIdiom = el.select("div[class=col-5 col-sm-3 ang]").first().firstElementChild().attr("href");

        return PREFIX_LINK +linkToIdiom;
    }

    public String getId() {
        counter=counter+1 ;
        return String.valueOf(counter);
    }
}
