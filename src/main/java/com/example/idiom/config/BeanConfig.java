package com.example.idiom.config;

import com.example.idiom.repository.idiom.IdiomDBService;
import com.example.idiom.service.ImpSelector;
import com.example.idiom.service.idiom.*;
import com.example.idiom.service.nooption.DefaultImplAngPl;
import com.example.idiom.service.phrasal.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new IdiomImpl(idiomParser(), idiomElement(), idiomAudioGrabber(), idiomCsVConverter(), idiomDBService());
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
    public ImpSelector chooseClass() {
        return new ImpSelector(phrasalVerbs(), idiomImpl(), defaultImplAngPl());
    }

    @Bean
    public DefaultImplAngPl defaultImplAngPl() {
        return new DefaultImplAngPl();
    }

    @Bean
    public IdiomDBService idiomDBService() {
        return new IdiomDBService();
    }

}
