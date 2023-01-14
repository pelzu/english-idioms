package com.example.idiom.service.dataGrab;

import com.example.idiom.service.dataGrab.idiom.*;
import com.example.idiom.service.dataGrab.phrasal.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

@Configuration
public class BeanConfig {

    @Bean
    public IdiomPagination idiomPagination() {
        return new IdiomPagination();
    }

    @Bean
    public IdiomParser idiomParser() {
        return new IdiomParser();
    }

    @Bean
    public IdiomAudioGrabber idiomAudioGrabber() {
        return new IdiomAudioGrabber();
    }

    @Bean
    public IdiomCsVConverter idiomCsVConverter() {
        return new IdiomCsVConverter();
    }



    @Bean
    public IdiomElement idiomElement() {
        return new IdiomElement(idiomPagination());
    }

    @Bean
    public IdiomImpl idiomImpl() {
        return new IdiomImpl(idiomParser(), idiomElement(), idiomAudioGrabber(), idiomCsVConverter());
    }

    @Bean
    public PhrasalVerbsParser phrasalVerbsParser() {
        return new PhrasalVerbsParser();
    }

    @Bean
    public PhrasalElement phrasalElement() {
        return new PhrasalElement(phrasalPagination());
    }

    @Bean
    public PhrasalPagination phrasalPagination() {
        return new PhrasalPagination();
    }

    @Bean
    public PhrasalCsvConverter phrasalCsvConverter() {
        return new PhrasalCsvConverter();
    }

    @Bean
    public PhrasalVerbsImpl phrasalVerbs() {
        return new PhrasalVerbsImpl(phrasalVerbsParser(), phrasalElement(), phrasalCsvConverter());
    }

    @Bean
    public ChooseClass chooseClass() {
        return new ChooseClass(phrasalVerbs(), idiomImpl(), defaultImplAngPl());
    }

    @Bean
    public DefaultImplAngPl defaultImplAngPl() {
        return new DefaultImplAngPl();
    }

}
