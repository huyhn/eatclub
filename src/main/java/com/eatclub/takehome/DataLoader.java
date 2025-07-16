package com.eatclub.takehome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Component;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

@Component
public class DataLoader {
    @Value("classpath:data/challengedata.json")
    private Resource file;

    public String getData() throws IOException {
        return new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

}
