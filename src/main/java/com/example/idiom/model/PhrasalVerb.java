package com.example.idiom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PhrasalVerb {

    private String id;
    private String polishMeaning;
    private String englishMeaning;
    private String englishExample;
    private String linkToPhrasalVerb;

    @Override
    public String toString() {
        return "PhrasalVerb{" +
                "id='" + id + '\'' +
                ", polishMeaning='" + polishMeaning + '\'' +
                ", englishMeaning='" + englishMeaning + '\'' +
                ", englishExample='" + englishExample + '\'' +
                ", linkToPhrasalVerb='" + linkToPhrasalVerb + '\'' +
                '}';
    }
}
