package com.eatclub.takehome;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Component;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

@Component
public class DataLoader {
    @Value("classpath:data/challengedata.json")
    private Resource file;

    public JSONObject getData() throws IOException, ParseException {
        String data = new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(data);
    }

}
