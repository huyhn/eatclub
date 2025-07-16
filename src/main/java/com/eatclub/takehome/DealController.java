package com.eatclub.takehome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DealController {

    @Autowired private DealService dealService;

    @GetMapping("/deals")
    List<RestaurantDeal> deals(@RequestParam("timeOfDay") Optional<String> timeOfDay) {
        if (timeOfDay.isEmpty()) {
            return new ArrayList<>();
        }
        return dealService.getActiveDeals(timeOfDay.get());
    }

    @GetMapping("/deals/peak")
    PeakPeriod peak() {
        return dealService.getPeak();
    }
}
