package com.example.idiom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Idiom {


    private String id;
    private String polishMeaning;
    private String englishMeaning;
    private String englishExample;
    private String audioTranslateLink;
    private String audioExampleLink;
    private String linkToIdiom;

    @Override
    public String toString() {
        return "IdiomModel{" +
                "id='" + id + '\'' +
                ", polishMeaning='" + polishMeaning + '\'' +
                ", englishMeaning='" + englishMeaning + '\'' +
                ", englishExample='" + englishExample + '\'' +
                ", audioTranslateLink='" + audioTranslateLink + '\'' +
                ", audioExampleLink='" + audioExampleLink + '\'' +
                ", linkToIdiom='" + linkToIdiom + '\'' +
                '}';
    }
}
