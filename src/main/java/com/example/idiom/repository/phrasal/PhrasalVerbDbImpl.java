package com.example.idiom.repository.phrasal;

import com.example.idiom.model.phrasal.PhrasalVerb;

import java.util.List;

public class PhrasalVerbDbImpl {

    private final PhrasalRepository phrasalRepository;


    public PhrasalVerbDbImpl(PhrasalRepository phrasalRepository) {
        this.phrasalRepository = phrasalRepository;
    }

    public void savePhrasalVerbs(List<PhrasalVerb> phrasalVerbList) {
        phrasalRepository.saveAll(phrasalVerbList);
    }
    public List<PhrasalVerb> getPhrasalVerbs() {
        return phrasalRepository.findAll();
    }
}
