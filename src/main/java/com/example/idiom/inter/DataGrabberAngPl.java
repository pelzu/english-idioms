package com.example.idiom.inter;

import java.io.IOException;

public interface DataGrabberAngPl {
    String PREFIX_LINK = "https://www.ang.pl";
    String PHRASAL_VERB_LINK = "https://www.ang.pl/slownictwo/phrasal-verbs/page/";

    void getObject() throws IOException;


}

