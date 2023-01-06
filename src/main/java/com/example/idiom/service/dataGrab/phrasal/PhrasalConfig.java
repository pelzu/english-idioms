package com.example.idiom.service.dataGrab.phrasal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhrasalConfig {

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
    public PhrasalVerbsImpl phrasalVerbs(){return new PhrasalVerbsImpl(phrasalVerbsParser(),phrasalElement(),phrasalCsvConverter());}

}
