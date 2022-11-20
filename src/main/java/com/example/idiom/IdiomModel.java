package com.example.idiom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdiomModel {


    private int id;
    private String polishMeaning;
    private String englishMeaning;
    private String englishExample;
}
