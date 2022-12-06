package com.example.idiom.inter;

import com.example.idiom.model.PhrasalVerb;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PhrasalVerbsInterface extends DataGrabberAngPl{
    String BASE_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/";

    String PREFIX_LINK = "https://www.ang.pl";

    String PHRASAL_POLISH_TRANSLATION ="p[class=pol]";

    void getPhrasalVerbs() throws IOException;
    List<PhrasalVerb> parseToPhrasalVerbs (Elements elements) ;


}
