package com.example.idiom.service.phrasal;

import com.example.idiom.service.DataGrabberAngPl;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.example.idiom.repository.phrasal.PhrasalVerbDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class PhrasalVerbsImpl implements DataGrabberAngPl, Predicate<String > {

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
    public List<PhrasalVerb> getList(String audio, String csv) {
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


    @Override
    public boolean test(String kind) {
        return kind.equals("phrasal");
    }
}
