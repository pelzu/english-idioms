package com.example.idiom.service.phrasal;

import com.example.idiom.model.phrasal.PhrasalVerb;
import com.example.idiom.repository.phrasal.PhrasalVerbDbImpl;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class PhrasalVerbsImpl implements IdiomAndPhrasalInterface, Predicate<String> {

    private final PhrasalVerbsParser phrasalVerbsParser;

    private final PhrasalElementScrapper phrasalElementScrapper;

    private final PhrasalCsvConverter phrasalCsvConverter;


    private final PhrasalVerbDbImpl phrasalVerbDbImpl;

    public PhrasalVerbsImpl(PhrasalVerbsParser phrasalVerbsParser, PhrasalElementScrapper phrasalElementScrapper, PhrasalCsvConverter phrasalCsvConverter, PhrasalVerbDbImpl phrasalVerbDbImpl) {
        this.phrasalVerbsParser = phrasalVerbsParser;
        this.phrasalElementScrapper = phrasalElementScrapper;
        this.phrasalCsvConverter = phrasalCsvConverter;
        this.phrasalVerbDbImpl = phrasalVerbDbImpl;
    }

    @Override
    public List<PhrasalVerb> getIdiomOrPhrasalList(String audio, String csv) {
        List<PhrasalVerb> phrasalVerbList = phrasalVerbsParser.parseElementsToPhrasalVerbList(phrasalElementScrapper.getPhrasalElements());

        if (audio != null) {
            if (audio.equals("true")) {
            }
        }
        if (csv != null) {
            if (csv.equals("true")) {
                phrasalCsvConverter.savePhrasalVerbToCsvFile(phrasalVerbList);
            }
        }
        phrasalVerbDbImpl.savePhrasalVerbs(phrasalVerbList);

        return phrasalVerbList;

    }


    @Override
    public boolean test(String kind) {
        return kind.equals("phrasal");
    }
}

