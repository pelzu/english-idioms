package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.service.inter.DataGrabberAngPl;
import com.example.idiom.model.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PhrasalVerbsImpl implements DataGrabberAngPl<PhrasalVerb> {

    private final PhrasalVerbsParser phrasalVerbsParser;

    private final PhrasalElement phrasalElement;

    @Autowired
    public PhrasalVerbsImpl(PhrasalVerbsParser phrasalVerbsParser, PhrasalElement phrasalElement) {
        this.phrasalVerbsParser = phrasalVerbsParser;
        this.phrasalElement = phrasalElement;
    }


    @Override
    public List<PhrasalVerb> getObject() {
        return phrasalVerbsParser.parseToPhrasalVerbs(phrasalElement.getElements());
    }


}
