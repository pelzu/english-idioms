package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.inter.DataGrabberAngPl;
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

    private final PhrasalCsvConverter phrasalCsvConverter;

    public PhrasalVerbsImpl(PhrasalVerbsParser phrasalVerbsParser, PhrasalElement phrasalElement, PhrasalCsvConverter phrasalCsvConverter) {
        this.phrasalVerbsParser = phrasalVerbsParser;
        this.phrasalElement = phrasalElement;
        this.phrasalCsvConverter = phrasalCsvConverter;
    }

    @Override
    public List<PhrasalVerb> getObject(String kind, String audio, String csv) {
        List<PhrasalVerb> phrasalVerbList=phrasalVerbsParser.parseToPhrasalVerbs(phrasalElement.getElements()) ;
        if (audio.equals("true")){}
        if (csv.equals("true")){phrasalCsvConverter.save(phrasalVerbList);}
        return phrasalVerbList ;
    }


}
