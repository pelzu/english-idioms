package com.example.idiom.service.dataGrab.idiom;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.model.idiom.IdiomDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IdiomDBService {

    @Autowired
    private IdiomDao idiomDao;


    public void saveIdiomListToDb(List<Idiom> idiomList){
        idiomDao.saveAllIdioms(idiomList);
    }


}
