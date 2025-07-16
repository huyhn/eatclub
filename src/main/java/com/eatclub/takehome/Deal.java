package com.eatclub.takehome;

public record Deal(
        String objectId,
        String discount,
        boolean dineIn,
        boolean lightning,
        String open,
        String close,
        int quantityLeft
){
}
