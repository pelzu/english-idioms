package com.example.idiom.model.idiom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Idiom {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String polishMeaning;
    private String englishMeaning;
    private String englishExample;
    private String audioTranslateLink;
    private String audioExampleLink;
    private String linkToIdiom;


    @Override
    public String toString() {
        return "\n" +
                "Idiom{" +
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

