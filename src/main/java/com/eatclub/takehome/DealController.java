package com.eatclub.takehome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DealController {

    @GetMapping("/deals")
    List<Deal> deals(@RequestParam("timeOfDay") Optional<String> timeOfDay) {
        if (timeOfDay.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @GetMapping("/peak")
    PeakPeriod peak() {
        return new PeakPeriod("..", "..");
    }
}
