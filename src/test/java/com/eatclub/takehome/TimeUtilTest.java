package com.eatclub.takehome;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TimeUtilTest {
    @Test
    public void convertTimeTest() {
        assertEquals(TimeUtil.convertTime("00:01"), 1);
        assertEquals(TimeUtil.convertTime("01:01am"), 61);
        assertEquals(TimeUtil.convertTime("12:00pm"), 720);
        assertEquals(TimeUtil.convertTime("12:01am"), 1);
        assertEquals(TimeUtil.convertTime("12:01"), 1);
        assertEquals(TimeUtil.convertTime("01:01"), 61);
        assertEquals(TimeUtil.convertTime("01:01pm"), 781);
        assertEquals(TimeUtil.convertTime("11:59pm"), 1439);
    }
}
