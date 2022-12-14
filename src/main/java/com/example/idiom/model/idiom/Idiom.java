package com.example.idiom.model.idiom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Idiom  {

    private String id;
    private String polishMeaning;
    private String englishMeaning;
    private String englishExample;
    private String audioTranslateLink;
    private String audioExampleLink;
    private String linkToIdiom;





}

