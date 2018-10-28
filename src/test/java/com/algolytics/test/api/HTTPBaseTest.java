package com.algolytics.test.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class HTTPBaseTest {

    String json(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
