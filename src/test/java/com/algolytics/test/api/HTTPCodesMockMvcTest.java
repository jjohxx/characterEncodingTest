package com.algolytics.test.api;

import com.algolytics.test.Application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class HTTPCodesMockMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testMockMvcOk() throws Exception {
        MyRequest request = new MyRequest();
        MyResponse response = new MyResponse();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/encoding")
                .content(json(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(json(response)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldPl").value(response.getFieldPl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldEn").value(response.getFieldEn()));
    }

    @Test
    public void testMockMvcFailsOnUnsupportedMediaType() throws Exception {
        MyRequest request = new MyRequest();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/encoding")
                .content(json(request))
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    private String json(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
