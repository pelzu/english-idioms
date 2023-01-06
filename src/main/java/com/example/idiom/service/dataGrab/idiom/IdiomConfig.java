package com.example.idiom.service.dataGrab.idiom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdiomConfig {

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

}
