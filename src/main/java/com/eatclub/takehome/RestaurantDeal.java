package com.eatclub.takehome;

public record RestaurantDeal(
        String restaurantObjectId,
        String restaurantName,
        String restaurantAddress1,
        String restaurantSuburb,
        String restaurantOpen,
        String restaurantClose,
        String dealObjectId,
        String discount,

        String dineIn,
        String lightning,
        String qtyLeft
) { }
