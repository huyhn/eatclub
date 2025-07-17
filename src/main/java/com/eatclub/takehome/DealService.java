package com.eatclub.takehome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DealService {

    @Autowired RestaurantRepository restaurantRepository;

    protected Map<String, RestaurantDeal> getAllDeals() {
        Map<String, RestaurantDeal> deals = new HashMap<>();
        for (Restaurant restaurant : restaurantRepository.all().values()) {
            for (Deal deal : restaurant.deals()) {
                deals.put(deal.objectId(), new RestaurantDeal(
                        restaurant.objectId(),
                        restaurant.name(),
                        restaurant.address1(),
                        restaurant.suburb(),
                        deal.open().isBlank() ? restaurant.open() : deal.open(),
                        deal.close().isBlank() ? restaurant.close() : deal.close(),
                        deal.objectId(),
                        deal.discount(),
                        deal.dineIn(),
                        deal.lightning(),
                        deal.quantityLeft()
                ));
            }
        }
        return deals;
    }

    public List<RestaurantDeal> getActiveDeals(String timeOfDay) {
        Map<String, RestaurantDeal> deals = this.getAllDeals();
        List<TimePeriod> periods = getTimePeriods(deals);

        int currentTime = TimeUtil.convertTime(timeOfDay);
        List<RestaurantDeal> restaurantDeals = new ArrayList<>();
        for (TimePeriod period : periods) {
            if (currentTime >= period.start && currentTime <= period.end) {
                RestaurantDeal deal = deals.get(period.id);
                Restaurant restaurant = restaurantRepository.all().get(deal.restaurantObjectId());
                deal = new RestaurantDeal(
                        deal.restaurantObjectId(),
                        deal.restaurantName(),
                        deal.restaurantAddress1(),
                        deal.restaurantSuburb(),
                        restaurant.open(),    // update with restaurant open time and not deal open time
                        restaurant.close(),   // update with restaurant close time and not deal close time
                        deal.dealObjectId(),
                        deal.discount(),
                        deal.dineIn(),
                        deal.lightning(),
                        deal.qtyLeft()
                );
                restaurantDeals.add(deal);
            }
        }
        return restaurantDeals;
    }

    public PeakPeriod getPeak() {
        Map<String, RestaurantDeal> deals = this.getAllDeals();
        List<Timestamp> timestamps = new ArrayList<>();
        for (RestaurantDeal deal : deals.values()) {
            timestamps.add(new Timestamp(TimeUtil.convertTime(deal.restaurantOpen()), 0));
            timestamps.add(new Timestamp(TimeUtil.convertTime(deal.restaurantClose()), 1));
        }
        timestamps.sort(Comparator.comparing(Timestamp::timestamp).thenComparing(Timestamp::isEnd));

        int maxCount = 0;
        int count = 0;
        int start = 0;
        int end = 0;
        for (Timestamp t : timestamps) {
            if (t.isEnd == 0) {
                count++;
                if (count > maxCount) {
                    start = t.timestamp;
                    maxCount = count;
                }
            } else {
                if (count == maxCount) {
                    end = t.timestamp;
                }
                count--;
            }
        }
        return new PeakPeriod(TimeUtil.convertTime(start), TimeUtil.convertTime(end));
    }

    private List<TimePeriod> getTimePeriods( Map<String, RestaurantDeal> deals) {
        List<TimePeriod> periods = new ArrayList<>();
        for (RestaurantDeal deal : deals.values()) {
            int start = TimeUtil.convertTime(deal.restaurantOpen());
            int end = TimeUtil.convertTime(deal.restaurantClose());
            if (start <= end) {
                periods.add(new TimePeriod(deal.dealObjectId(), start, end));
            } else {
                periods.add(new TimePeriod(deal.dealObjectId(), start, TimeUtil.convertTime("11:59pm")));
                periods.add(new TimePeriod(deal.dealObjectId(), 0, end));
            }
        }
        periods.sort(Comparator.comparingInt(o -> o.start));
        return periods;
    }

    private record TimePeriod (String id, int start, int end) {}
    private record Timestamp (int timestamp, int isEnd) {}

}
