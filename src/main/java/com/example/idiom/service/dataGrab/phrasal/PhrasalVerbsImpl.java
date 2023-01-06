package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.model.phrasal.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PhrasalVerbsImpl implements DataGrabberAngPl {

    private final PhrasalVerbsParser phrasalVerbsParser;

    private final PhrasalElement phrasalElement;

    private final PhrasalCsvConverter phrasalCsvConverter;

    public PhrasalVerbsImpl(PhrasalVerbsParser phrasalVerbsParser, PhrasalElement phrasalElement, PhrasalCsvConverter phrasalCsvConverter) {
        this.phrasalVerbsParser = phrasalVerbsParser;
        this.phrasalElement = phrasalElement;
        this.phrasalCsvConverter = phrasalCsvConverter;
    }

    @Override
    public List<PhrasalVerb> getObject(String audio, String csv) {
        List<PhrasalVerb> phrasalVerbList = phrasalVerbsParser.parseToPhrasalVerbs(phrasalElement.getElements());
        if (audio != null) {
            if (audio.equals("true")) {
            }
        }
        if (csv != null) {
            if (csv.equals("true")) {
                phrasalCsvConverter.save(phrasalVerbList);
            }
        }
        return phrasalVerbList;


    }
}
