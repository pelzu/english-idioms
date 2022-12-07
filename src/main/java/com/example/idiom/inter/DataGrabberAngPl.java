package com.example.idiom.inter;

import com.example.idiom.model.PhrasalVerb;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DataGrabberAngPl {
    String PREFIX_LINK = "https://www.ang.pl";
    String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";

    void getObject() throws IOException;



}

