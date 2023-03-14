package com.example.idiom.repository.idiom;

import com.example.idiom.model.idiom.Idiom;

import java.util.List;


public class IdiomDBService {

    private final IdiomRepository idiomRepository;

    public IdiomDBService(IdiomRepository idiomRepository) {
        this.idiomRepository = idiomRepository;
    }

    public void saveIdiomList(List<Idiom> idiomList) {
        idiomRepository.saveAll(idiomList);
    }

    public List<Idiom> getIdiomList() {
        return idiomRepository.findAll();
    }


}
