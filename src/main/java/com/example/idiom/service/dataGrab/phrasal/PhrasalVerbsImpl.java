package com.example.idiom.service.dataGrab.phrasal;

import com.example.idiom.inter.DataGrabberAngPl;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.example.idiom.model.phrasal.PhrasalVerbDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class PhrasalVerbsImpl implements DataGrabberAngPl {

    private final PhrasalVerbsParser phrasalVerbsParser;

    private final PhrasalElement phrasalElement;

    private final PhrasalCsvConverter phrasalCsvConverter;

    @Autowired
    private PhrasalVerbDao phrasalVerbDao;

    public PhrasalVerbsImpl(PhrasalVerbsParser phrasalVerbsParser, PhrasalElement phrasalElement, PhrasalCsvConverter phrasalCsvConverter) {
        this.phrasalVerbsParser = phrasalVerbsParser;
        this.phrasalElement = phrasalElement;
        this.phrasalCsvConverter = phrasalCsvConverter;
    }

    @Override
    public List<PhrasalVerb> getObject(String audio, String csv) {
        List<PhrasalVerb> phrasalVerbList = phrasalVerbsParser.parseToPhrasalVerbs(phrasalElement.getElements());

        if (audio != null) {
            if (audio.equals("true")) {
            }
        }
        if (csv != null) {
            if (csv.equals("true")) {
                phrasalCsvConverter.save(phrasalVerbList);
            }
        }
        saveToDB(phrasalVerbList);
        return phrasalVerbList;


    }

    public void saveToDB(List<PhrasalVerb> phrasalVerbList) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.submit(() -> {
//            Thread.currentThread().setName("AudioDownloadThread");
            phrasalVerbDao.saveAllPhrasalToDb(phrasalVerbList);

//        });
//        executorService.shutdown();
    }


}

