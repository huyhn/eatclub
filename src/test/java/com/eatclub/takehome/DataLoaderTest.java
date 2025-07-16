package com.eatclub.takehome;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DataLoaderTest {

    @Autowired DataLoader loader;

    @Test
    public void dataLoaderTest() throws IOException {
        String data = loader.getData();
        assertFalse(data.isBlank());
    }
}
