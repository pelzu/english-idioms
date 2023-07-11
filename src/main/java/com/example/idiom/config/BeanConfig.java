package com.example.idiom.config;

import com.example.idiom.repository.idiom.IdiomDbImpl;
import com.example.idiom.repository.idiom.IdiomRepository;
import com.example.idiom.repository.phrasal.PhrasalRepository;
import com.example.idiom.repository.phrasal.PhrasalVerbDbImpl;
import com.example.idiom.service.ImplSelector;
import com.example.idiom.service.idiom.*;
import com.example.idiom.service.nooption.DefaultImplAngPl;
import com.example.idiom.service.phrasal.PhrasalCsvConverter;
import com.example.idiom.service.phrasal.PhrasalElementScrapper;
import com.example.idiom.service.phrasal.PhrasalVerbService;
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
    public IdiomService idiomImpl() {
        return new IdiomService(idiomParser(), idiomElement(), idiomAudioGrabber(), idiomCsVConverter(), idiomDbImpl());
    }


    @Bean
    public IdiomParser idiomParser() {
        return new IdiomParser();
    }

    @Bean
    public IdiomAudioDownloader idiomAudioGrabber() {
        return new IdiomAudioDownloader();
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
    public IdiomDbImpl idiomDbImpl() {
        return new IdiomDbImpl(idiomRepository);
    }

    @Bean
    public PhrasalVerbService phrasalVerbs() {
        return new PhrasalVerbService(phrasalVerbsParser(), phrasalElement(), phrasalCsvConverter(), phrasalVerbDbImpl());
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
    public PhrasalVerbDbImpl phrasalVerbDbImpl() {
        return new PhrasalVerbDbImpl(phrasalRepository);
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
