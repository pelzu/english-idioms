package com.example.idiom.repository.idiom;

import com.example.idiom.model.idiom.Idiom;

import java.util.List;


public class IdiomDbService {

    private final IdiomRepository idiomRepository;

    public IdiomDbService(IdiomRepository idiomRepository) {
        this.idiomRepository = idiomRepository;
    }

    public void saveIdioms(List<Idiom> idiomList) {
        idiomRepository.saveAll(idiomList);
    }

    public List<Idiom> getIdioms() {
        return idiomRepository.findAll();
    }


}
