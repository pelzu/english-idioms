package com.example.idiom.repository.idiom;

import com.example.idiom.model.idiom.Idiom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IdiomDBService {

    @Autowired
    private IdiomDao idiomDao;


    public void saveIdiomListToDb(List<Idiom> idiomList) {
        idiomDao.saveAllIdioms(idiomList);
    }

    public List<Idiom> getIdiomListFromDB() {
        return idiomDao.getIdiomList();
    }


}
