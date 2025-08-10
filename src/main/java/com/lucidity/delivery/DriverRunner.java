package com.lucidity.delivery;

import com.lucidity.delivery.domain.*;
import com.lucidity.delivery.service.BatchOptimizationService;
import com.lucidity.delivery.service.RestaurantService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DriverRunner {

    private final RestaurantService restaurantService;
    private final BatchOptimizationService optimizationService;

    public DriverRunner(RestaurantService restaurantService,
                        BatchOptimizationService optimizationService) {
        this.restaurantService = restaurantService;
        this.optimizationService = optimizationService;
    }

    public void run() {
        DeliveryPerson rider = new DeliveryPerson(UUID.randomUUID().toString(), "Aman",
                new GeoLocation(12.934533, 77.626579));

        Restaurant r1 = new Restaurant(UUID.randomUUID().toString(), "R1", new GeoLocation(12.938, 77.620), 15);
        Restaurant r2 = new Restaurant(UUID.randomUUID().toString(), "R2", new GeoLocation(12.931, 77.629), 10);
        restaurantService.saveRestaurant(r1);
        restaurantService.saveRestaurant(r2);

        Order o1 = new Order(UUID.randomUUID().toString(), "Consumer 1", new GeoLocation(12.936, 77.622), r1.getId());
        Order o2 = new Order(UUID.randomUUID().toString(), "Consumer 2", new GeoLocation(12.930, 77.634), r2.getId());

        BatchPlan plan = optimizationService.computeOptimalPlan(rider, List.of(o1, o2));

        System.out.println("Optimal Plan (single-agent, two orders):");
        plan.getSteps().forEach(step -> System.out.println(" - " + step));
        System.out.printf("Total time: %.2f minutes%n", plan.getTotalMinutes());

        System.out.println("Per-order ETAs:");
        plan.getOrderIdToEta().values().forEach(eta ->
                System.out.printf(" - Order %s to %s: %.2f minutes%n", eta.getOrderId(), eta.getConsumerName(), eta.getEtaMinutes())
        );
    }

} 