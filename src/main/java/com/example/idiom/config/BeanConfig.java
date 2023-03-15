package com.example.idiom.config;

import com.example.idiom.repository.idiom.IdiomDbService;
import com.example.idiom.repository.idiom.IdiomRepository;
import com.example.idiom.repository.phrasal.PhrasalRepository;
import com.example.idiom.repository.phrasal.PhrasalVerbDbService;
import com.example.idiom.service.ImplSelector;
import com.example.idiom.service.idiom.*;
import com.example.idiom.service.nooption.DefaultImplAngPl;
import com.example.idiom.service.phrasal.PhrasalCsvConverter;
import com.example.idiom.service.phrasal.PhrasalElementScrapper;
import com.example.idiom.service.phrasal.PhrasalVerbsImpl;
import com.example.idiom.service.phrasal.PhrasalVerbsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    private IdiomRepository idiomRepository;
    private PhrasalRepository phrasalRepository;

    @Autowired
    public BeanConfig(IdiomRepository idiomRepository, PhrasalRepository phrasalRepository) {
        this.idiomRepository = idiomRepository;
        this.phrasalRepository = phrasalRepository;
    }

    @Bean
    public IdiomImpl idiomImpl() {
        return new IdiomImpl(idiomParser(), idiomElement(), idiomAudioGrabber(), idiomCsVConverter(), idiomDBService());
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
    public IdiomCsvConverter idiomCsVConverter() {
        return new IdiomCsvConverter();
    }


    @Bean
    public IdiomElementScrapper idiomElement() {
        return new IdiomElementScrapper();
    }


    @Bean
    public IdiomDbService idiomDBService() {
        return new IdiomDbService(idiomRepository);
    }

    @Bean
    public PhrasalVerbsImpl phrasalVerbs() {
        return new PhrasalVerbsImpl(phrasalVerbsParser(), phrasalElement(), phrasalCsvConverter(), phrasalVerbDbService());
    }

    @Bean
    public PhrasalVerbsParser phrasalVerbsParser() {
        return new PhrasalVerbsParser();
    }

    @Bean
    public PhrasalElementScrapper phrasalElement() {
        return new PhrasalElementScrapper();
    }

    @Bean
    public PhrasalCsvConverter phrasalCsvConverter() {
        return new PhrasalCsvConverter();
    }

    @Bean
    public PhrasalVerbDbService phrasalVerbDbService() {
        return new PhrasalVerbDbService(phrasalRepository);
    }

    @Bean
    public ImplSelector chooseClass() {
        return new ImplSelector(phrasalVerbs(), idiomImpl(), defaultImplAngPl());
    }

    @Bean
    public DefaultImplAngPl defaultImplAngPl() {
        return new DefaultImplAngPl();
    }


}
