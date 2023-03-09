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

    private final IdiomElement idiomElement;

    private final IdiomAudioGrabber idiomAudioGrabber;

    private final IdiomCsVConverter idiomCsVConverter;

    private final IdiomDBService idiomDBService;

    public IdiomImpl(IdiomParser idiomParser, IdiomElement idiomElement, IdiomAudioGrabber idiomAudioGrabber, IdiomCsVConverter idiomCsVConverter, IdiomDBService idiomDBService) {
        this.idiomParser = idiomParser;
        this.idiomElement = idiomElement;
        this.idiomAudioGrabber = idiomAudioGrabber;
        this.idiomCsVConverter = idiomCsVConverter;
        this.idiomDBService = idiomDBService;
    }

    @Override
    public List<Idiom> getIdiomOrPhrasalList(String audio, String csv) {

        List<Idiom> idiomList = idiomParser.parseToIdiom(idiomElement.getElements());
        if (csv != null) {
            if (csv.equals("true")) {
                idiomCsVConverter.save(idiomList);
            }
        }
        if (audio != null) {
            if (audio.equals("true")) {
                idiomAudioGrabber.downLoadAudio(idiomList);
            }
        }
        idiomDBService.saveIdiomListToDb(idiomList);
        return idiomList;
    }

    @Override
    public boolean test(String kind) {
        return kind.equals("idiom");
    }
}
