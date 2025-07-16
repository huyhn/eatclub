package com.eatclub.takehome;

public record Deal(
        String restaurantId,
        String objectId,
        String discount,
        String dineIn,
        String lightning,
        String open,
        String close,
        String quantityLeft
){
}
