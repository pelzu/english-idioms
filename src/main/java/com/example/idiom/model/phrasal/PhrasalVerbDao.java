package com.example.idiom.model.phrasal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class PhrasalVerbDao {

    public PhrasalRepository phrasalRepository;

    @Autowired
    public PhrasalVerbDao(PhrasalRepository phrasalRepository) {
        this.phrasalRepository = phrasalRepository;
    }

    public void saveAllPhrasalToDb(List<PhrasalVerb> phrasalVerbList) {
        phrasalRepository.saveAll(phrasalVerbList);
    }
}
