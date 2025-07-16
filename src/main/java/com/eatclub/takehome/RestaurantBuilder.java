package com.eatclub.takehome;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class RestaurantBuilder {
    private RestaurantBuilder() {}
    public static Restaurant build(JSONObject jsonObject) {
        List<String> cuisines = (List<String>) jsonObject.get("cuisines");
        List<Deal> deals = new ArrayList<>();
        for (JSONObject deal : (List<JSONObject>) jsonObject.get("deals")) {
            deals.add(new Deal(
                    (String) deal.get("objectId"),
                    (String) deal.get("discount"),
                    Boolean.parseBoolean((String) deal.get("dineIn")),
                    Boolean.parseBoolean((String) deal.get("lightning")),
                    (String) deal.getOrDefault("open", ""),
                    (String) deal.getOrDefault("close", ""),
                    Integer.parseInt((String) deal.get("qtyLeft"))
            ));
        }

        return new Restaurant(
                (String) jsonObject.get("objectId"),
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
