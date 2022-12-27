package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.model.idiom.Idiom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IdiomImpl implements DataGrabberAngPl {
    private final IdiomParser idiomParser;

    private final IdiomElement idiomElement;

    private final IdiomAudioGrabber idiomAudioGrabber;

    private final IdiomCsVConverter idiomCsVConverter;

    @Autowired
    public IdiomImpl(IdiomParser idiomParser, IdiomElement idiomElement, IdiomAudioGrabber idiomAudioGrabber, IdiomCsVConverter idiomCsVConverter) {
        this.idiomParser = idiomParser;
        this.idiomElement = idiomElement;
        this.idiomAudioGrabber = idiomAudioGrabber;
        this.idiomCsVConverter = idiomCsVConverter;
    }

    @Override
    public List<Idiom> getObject(String audio, String csv) {

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

        return idiomList;
    }
}
