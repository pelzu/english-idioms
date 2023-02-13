package com.example.idiom.repository.phrasal;

import com.example.idiom.model.phrasal.PhrasalVerb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PhrasalVerbDao {

    private final PhrasalRepository phrasalRepository;

    @Autowired
    public PhrasalVerbDao(PhrasalRepository phrasalRepository) {
        this.phrasalRepository = phrasalRepository;
    }

    public void saveAllPhrasalToDb(List<PhrasalVerb> phrasalVerbList) {
        phrasalRepository.saveAll(phrasalVerbList);
    }
}
