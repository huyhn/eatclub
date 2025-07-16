package com.eatclub.takehome;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DataLoaderTest {

    @Autowired DataLoader loader;

    @Test
    public void dataLoaderTest() throws IOException, ParseException {
        JSONObject jsonObject = loader.getData();
        List<JSONObject> restaurants = (List<JSONObject>) jsonObject.get("restaurants");

        assertFalse(restaurants.isEmpty());
        assertEquals(restaurants.size(), 6);
    }
}
