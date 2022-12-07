package com.example.idiom.service.phrasal;

import com.example.idiom.inter.DataGrabberAngPl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class PhrasalVerbsImpl implements DataGrabberAngPl {

    private final PhrasalVerbsParser phrasalVerbsParser;

    private final PhrasalElement phrasalElement;

    @Autowired
    public PhrasalVerbsImpl(PhrasalVerbsParser phrasalVerbsParser, PhrasalElement phrasalElement) {
        this.phrasalVerbsParser = phrasalVerbsParser;
        this.phrasalElement = phrasalElement;
    }


    @Override
    public void getObject() throws IOException {
        phrasalVerbsParser.parseToPhrasalVerbs(phrasalElement.getElements());
    }


}
