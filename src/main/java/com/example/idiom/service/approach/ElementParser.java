package com.example.idiom.service.approach;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
public class ElementParser {

    public static Elements getChildOfElement(Element element,String selector){
        Elements dataColumn= element.children().select(selector);
        return dataColumn;
    }

}


