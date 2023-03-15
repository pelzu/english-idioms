package com.example.idiom.service.idiom;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.repository.idiom.IdiomDbService;
import com.example.idiom.service.IdiomAndPhrasalInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class IdiomImpl implements IdiomAndPhrasalInterface, Predicate<String> {
    private final IdiomParser idiomParser;

    private final IdiomElementScrapper idiomElementScrapper;

    private final IdiomAudioDownloader idiomAudioDownloader;

    private final IdiomCsvConverter idiomCsvConverter;



    public IdiomImpl(IdiomParser idiomParser, IdiomElementScrapper idiomElementScrapper, IdiomAudioDownloader idiomAudioDownloader, IdiomCsvConverter idiomCsvConverter, IdiomDbService idiomDBService) {
        this.idiomParser = idiomParser;
        this.idiomElementScrapper = idiomElementScrapper;
        this.idiomAudioDownloader = idiomAudioDownloader;
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

                idiomAudioDownloader.downloadIdiomsAudios(idiomList);
            }
        }
        return idiomList;
    }

    @Override
    public boolean test(String kind) {
        return kind.equals("idiom");
    }
}
