package com.eatclub.takehome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DealService {

    @Autowired RestaurantRepository restaurantRepository;

    protected Map<String, RestaurantDeal> getAllDeals() {
        Map<String, RestaurantDeal> deals = new HashMap<>();
        for (Restaurant restaurant : restaurantRepository.all()) {
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
                restaurantDeals.add(deals.get(period.id));
            }
        }
        return restaurantDeals;
    }

    public PeakPeriod getPeak() {
        Map<String, RestaurantDeal> deals = this.getAllDeals();
        List<Timestamp> timestamps = new ArrayList<>();
        for (RestaurantDeal deal : deals.values()) {
            timestamps.add(new Timestamp(TimeUtil.convertTime(deal.restaurantOpen()), 1));
            timestamps.add(new Timestamp(TimeUtil.convertTime(deal.restaurantClose()), 0));
        }
        timestamps.sort(Comparator.comparing(Timestamp::timestamp).thenComparing(Timestamp::isStart));

        int maxCount = 0;
        int count = 0;
        int start = 0;
        int end = 0;
        for (Timestamp t : timestamps) {
            if (t.isStart == 1) {
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
    private record Timestamp (int timestamp, int isStart) {}

}
