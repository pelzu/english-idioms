package com.example.idiom.service.dataGrab;

import com.example.idiom.inter.DataGrabberAngPl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DefaultImplAngPl implements DataGrabberAngPl<String> {
    @Override
    public List<String> getObject(String audio, String csv) {
        List<String > stringList =
                Arrays.asList(
                        "Are you trying get kind:"+ " try write phrasal or idiom",
                        "Download audio? Your choice is:"+audio+ " choose true to download (available idiom only)",
                        "Save to csv file ?Your choice is:"+csv+ " choose true to write csv file");

        log.warn("Wrong param    "+" kind:"+" audio:"+audio+" csv:"+csv);
        return stringList;
    }
}
