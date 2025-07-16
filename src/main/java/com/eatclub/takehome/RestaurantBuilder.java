package com.eatclub.takehome;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class RestaurantBuilder {
    private RestaurantBuilder() {}
    public static Restaurant build(JSONObject jsonObject) {
        String restaurantId = (String) jsonObject.get("objectId");
        List<String> cuisines = (List<String>) jsonObject.get("cuisines");
        List<Deal> deals = new ArrayList<>();
        for (JSONObject deal : (List<JSONObject>) jsonObject.get("deals")) {
            deals.add(new Deal(
                    restaurantId,
                    (String) deal.get("objectId"),
                    (String) deal.get("discount"),
                    (String) deal.get("dineIn"),
                    (String) deal.get("lightning"),
                    (String) deal.getOrDefault("open", ""),
                    (String) deal.getOrDefault("close", ""),
                    (String) deal.get("qtyLeft")
            ));
        }

        return new Restaurant(
                restaurantId,
                (String) jsonObject.get("name"),
                (String) jsonObject.get("address1"),
                (String) jsonObject.get("suburb"),
                (String) jsonObject.get("imageLink"),
                (String) jsonObject.get("open"),
                (String) jsonObject.get("close"),
                cuisines,
                deals
        );
    }
}
