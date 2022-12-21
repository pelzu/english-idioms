package com.example.idiom.controller;

import com.example.idiom.model.idiom.Idiom;
import com.example.idiom.model.phrasal.PhrasalVerb;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class IdiomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void PhrasalControllerGetPhrasals() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/learn?kind=phrasal"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        List<PhrasalVerb> phrasalVerbList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructType(List.class, PhrasalVerb.class));
        assertThat(phrasalVerbList.size()).isEqualTo(402);
    }

    @Test
    void IdiomControllerGetPhrasals() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/learn?kind=idiom"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        List<Idiom> idiomList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructType(List.class, Idiom.class));
        assertThat(idiomList.size()).isEqualTo(521);


    }
}
