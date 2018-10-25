package com.algolytics.test.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(method = RequestMethod.POST, value = "/api/encoding")
    public MyResponse handleEncoding(@RequestBody MyRequest request){
        String field = request.getField();
        System.out.println(field);
        return new MyResponse();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/encodingUTF8",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public MyResponse handleEncodingUTF8(@RequestBody MyRequest request){
        String field = request.getField();
        System.out.println(field);
        return new MyResponse();
    }
}
