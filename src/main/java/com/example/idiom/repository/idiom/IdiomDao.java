package com.example.idiom.repository.idiom;

import com.example.idiom.model.idiom.Idiom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomDao {

    private final IdiomRepository idiomRepository;

    @Autowired
    public IdiomDao(IdiomRepository idiomRepository) {
        this.idiomRepository = idiomRepository;
    }

    public void saveAllIdioms(List<Idiom> idiomList){
            idiomRepository.saveAll(idiomList);
    }
}
