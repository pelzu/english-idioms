package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.Idiom;
import com.example.idiom.service.inter.DataGrabberAngPl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IdiomImpl implements DataGrabberAngPl<Idiom> {
    private final IdiomParser idiomParser;

    private final IdiomElement idiomElement;

    @Autowired
    public IdiomImpl(IdiomParser idiomParser, IdiomElement idiomElement) {
        this.idiomParser = idiomParser;
        this.idiomElement = idiomElement;
    }

    @Override
    public List<Idiom> getObject() {
        return idiomParser.parseToIdiom(idiomElement.getElements());
    }
}
