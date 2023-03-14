package com.example.idiom.repository.phrasal;

import com.example.idiom.model.phrasal.PhrasalVerb;

import java.util.List;

public class PhrasalVerbDbService {

    private final PhrasalRepository phrasalRepository;


    public PhrasalVerbDbService(PhrasalRepository phrasalRepository) {
        this.phrasalRepository = phrasalRepository;
    }

    public void savePhrasalVerbList(List<PhrasalVerb> phrasalVerbList) {
        phrasalRepository.saveAll(phrasalVerbList);
    }
}
