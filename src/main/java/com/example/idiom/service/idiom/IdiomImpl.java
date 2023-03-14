package com.example.idiom.service.idiom;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.repository.idiom.IdiomDBService;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class IdiomImpl implements IdiomAndPhrasalInterface, Predicate<String> {
    private final IdiomParser idiomParser;

    private final IdiomElementScrapper idiomElementScrapper;

    private final IdiomAudioGrabber idiomAudioGrabber;

    private final IdiomCsvConverter idiomCsvConverter;



    public IdiomImpl(IdiomParser idiomParser, IdiomElementScrapper idiomElementScrapper, IdiomAudioGrabber idiomAudioGrabber, IdiomCsvConverter idiomCsvConverter, IdiomDBService idiomDBService) {
        this.idiomParser = idiomParser;
        this.idiomElementScrapper = idiomElementScrapper;
        this.idiomAudioGrabber = idiomAudioGrabber;
        this.idiomCsvConverter = idiomCsvConverter;
    }

    @Override
    public List<Idiom> getIdiomOrPhrasalList(String audio, String csv) {

        List<Idiom> idiomList = idiomParser.parseElementsToIdiomList(idiomElementScrapper.getElementsContainingIdioms());
        if (csv != null) {
            if (csv.equals("true")) {
                idiomCsvConverter.saveIdiomListToCsvFile(idiomList);
            }
        }
        if (audio != null) {
            if (audio.equals("true")) {

                idiomAudioGrabber.downloadAudioByIdiomList(idiomList);
            }
        }
        return idiomList;
    }

    @Override
    public boolean test(String kind) {
        return kind.equals("idiom");
    }
}
