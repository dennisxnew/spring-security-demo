package com.example.springsecuritydemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dennis.Chen
 * @Date
 */
@RestController
public class XMLContorller {

    @RequestMapping(path="/getXML")
    public Object getXML(HttpServletResponse response){
        Object result = new Object();
        //JSON
        response.setContentType("text/json");
        Map<String, String> map = new HashMap<>();
        map.put("a","b");
        result = map;

        //XML
        response.setContentType("text/xml");
        //call stix api
        result = "123";
//        response.setContentType("text/xml");
        return result;
    }
}
