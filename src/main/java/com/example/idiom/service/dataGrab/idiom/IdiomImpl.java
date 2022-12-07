package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.Idiom;
import com.example.idiom.inter.DataGrabberAngPl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IdiomImpl implements DataGrabberAngPl<Idiom> {
    private final IdiomParser idiomParser;

    private final IdiomElement idiomElement;


    private final IdiomCsVConverter idiomCsVConverter;

    @Autowired
    public IdiomImpl(IdiomParser idiomParser, IdiomElement idiomElement, IdiomCsVConverter idiomCsVConverter) {
        this.idiomParser = idiomParser;
        this.idiomElement = idiomElement;
        this.idiomCsVConverter = idiomCsVConverter;
    }

    @Override
    public List<Idiom> getObject() {
        List<Idiom> idiomList = idiomParser.parseToIdiom(idiomElement.getElements());
        idiomCsVConverter.save(idiomList);
        return idiomList;
    }
}
