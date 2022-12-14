package com.example.idiom.service.dataGrab;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.service.dataGrab.idiom.IdiomImpl;
import com.example.idiom.service.dataGrab.phrasal.PhrasalVerbsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j

public class DefaultImplAngPl implements DataGrabberAngPl{

    private IdiomImpl idiom ;
    private PhrasalVerbsImpl phrasalVerbs ;

    @Autowired
    public DefaultImplAngPl(IdiomImpl idiom, PhrasalVerbsImpl phrasalVerbs) {
        this.idiom = idiom;
        this.phrasalVerbs = phrasalVerbs;
    }

    @Override
    public List getObject(String audio, String csv) {
        
        return null;
    }
}
