package com.eatclub.takehome;

import java.util.List;

public record Restaurant(
        String objectId,
        String name,
        String address1,
        String suburb,
        String imageLink,
        String open,
        String close,
        List<String> cuisines,
        List<Deal> deals
        ) {}
