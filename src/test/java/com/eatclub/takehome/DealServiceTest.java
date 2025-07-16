package com.eatclub.takehome;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DealServiceTest {
    @Autowired
    DealService dealService;

    @Test
    public void dataLoaderTest() throws IOException, ParseException {
        List<RestaurantDeal> results = dealService.getActiveDeals("10:30");
        System.out.println(results);
        assertEquals(2, results.size());
    }
}
