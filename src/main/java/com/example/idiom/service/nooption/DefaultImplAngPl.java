package com.example.idiom.service.nooption;

import com.example.idiom.service.IdiomAndPhrasalInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class DefaultImplAngPl implements IdiomAndPhrasalInterface {

    @Override
    public List getIdiomOrPhrasalList(String audio, String csv) {
        List<String> defaultMsg = new ArrayList<>();
        defaultMsg.add("No object by this link ");
        defaultMsg.add("http://localhost:8000/learn?kind=idiom&audio=true&csv=true");
        defaultMsg.add("parameter kind can be idiom or phrasal ");
        defaultMsg.add("Choose audio=true to download mp3 file");
        defaultMsg.add("Choose csv=true to get CSV file");
        return defaultMsg;
    }
}
