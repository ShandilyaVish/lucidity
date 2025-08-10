package com.lucidity.delivery.service_impl;

import com.lucidity.delivery.domain.*;
import com.lucidity.delivery.dto.AssignmentOption;
import com.lucidity.delivery.dto.RoutePlan;
import com.lucidity.delivery.service.BatchOptimizationService;
import com.lucidity.delivery.service.DistanceService;
import com.lucidity.delivery.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BatchOptimizationServiceImpl implements BatchOptimizationService {

    private final DistanceService distanceService;
    private final RestaurantService restaurantService;

    public BatchOptimizationServiceImpl(DistanceService distanceService,
                                        RestaurantService restaurantService) {
        this.distanceService = distanceService;
        this.restaurantService = restaurantService;
    }

    @Override
    public BatchPlan computeOptimalPlan(List<DeliveryPerson> deliveryPeople, List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("At least one order is required");
        }
        if (deliveryPeople == null || deliveryPeople.isEmpty()) {
            throw new IllegalArgumentException("At least one delivery person is required");
        }

        Map<String, Restaurant> restaurantById = new HashMap<>();
        for (Order order : orders) {
            Restaurant r = restaurantService.getRestaurant(order.getRestaurantId());
            if (r == null) {
                throw new IllegalStateException("Missing restaurant for order: " + order.getId());
            }
            restaurantById.put(order.getRestaurantId(), r);
        }

        List<RoutePlan> routes = new ArrayList<>();
        for (DeliveryPerson dp : deliveryPeople) {
            routes.add(new RoutePlan(dp));
        }

        Set<String> assignedOrders = new HashSet<>();

        while (assignedOrders.size() < orders.size()) {
            PriorityQueue<AssignmentOption> pq = new PriorityQueue<>((a, b) -> {
                int cmp = Double.compare(a.getResultingMakespan(), b.getResultingMakespan());
                if (cmp != 0) return cmp;
                cmp = Double.compare(a.getDeltaMinutes(), b.getDeltaMinutes());
                if (cmp != 0) return cmp;
                return a.getOrderId().compareTo(b.getOrderId());
            });

            double[] currentOtherMax = new double[routes.size()];
            for (int i = 0; i < routes.size(); i++) {
                currentOtherMax[i] = otherRoutesMaxFinish(i, routes);
            }

            for (int i = 0; i < routes.size(); i++) {
                RoutePlan route = routes.get(i);
                double otherMax = currentOtherMax[i];
                for (Order order : orders) {
                    if (assignedOrders.contains(order.getId())) continue;
                    Restaurant r = restaurantById.get(order.getRestaurantId());
                    AssignmentOption cand = evaluateAppend(i, route, order, r, otherMax);
                    pq.offer(cand);
                }
            }

            if (pq.isEmpty()) {
                throw new IllegalStateException("No feasible assignments remaining");
            }

            AssignmentOption best = pq.poll();
            RoutePlan route = routes.get(best.getRouteIndex());
            Restaurant r = restaurantById.get(best.getRestaurantId());
            double eta = route.getCurrentMinutes() + distanceService.travelMinutes(route.getCurrentLocation(), r.getLocation());
            if (eta < r.getAveragePrepMinutes()) {
                eta = r.getAveragePrepMinutes();
            }
            eta += distanceService.travelMinutes(r.getLocation(), best.getConsumerLocation());
            route.recordOrderEta(best.getOrderId(), best.getConsumerName(), eta);

            appendOrderToRoute(route, best.getOrderId(), r, best.getConsumerName(), best.getConsumerLocation());
            assignedOrders.add(best.getOrderId());
        }

        return buildBatchPlan(routes);
    }

    private BatchPlan buildBatchPlan(List<RoutePlan> routes) {
        BatchPlan plan = new BatchPlan();
        final double[] sumOfETAs = {0.0};
        
        for (RoutePlan route : routes) {
            for (String step : route.getSteps()) {
                plan.addStep("[" + route.getDeliveryPerson().getName() + "] " + step);
            }
            route.getOrderEtas().forEach((orderId, orderEta) -> {
                plan.recordOrderEta(orderId, orderEta.getConsumerName(), orderEta.getEtaMinutes());
                sumOfETAs[0] += orderEta.getEtaMinutes();
            });
        }
        
        plan.setTotalMinutes(sumOfETAs[0]);
        return plan;
    }

    private double otherRoutesMaxFinish(int routeIndex, List<RoutePlan> routes) {
        double max = 0.0;
        for (int i = 0; i < routes.size(); i++) {
            if (i == routeIndex) continue;
            max = Math.max(max, routes.get(i).getCurrentMinutes());
        }
        return max;
    }

    private AssignmentOption evaluateAppend(int routeIndex,
                                            RoutePlan route,
                                            Order order,
                                            Restaurant restaurant,
                                            double otherMaxFinish) {
        GeoLocation current = route.getCurrentLocation();
        double t = route.getCurrentMinutes();
        double toRest = distanceService.travelMinutes(current, restaurant.getLocation());
        double arrivalAtRest = t + toRest;
        double wait = Math.max(0.0, restaurant.getAveragePrepMinutes() - arrivalAtRest);
        double toConsumer = distanceService.travelMinutes(restaurant.getLocation(), order.getConsumerLocation());
        double delta = toRest + wait + toConsumer;
        double finishThisRoute = t + delta;
        double resultingMakespan = Math.max(otherMaxFinish, finishThisRoute);
        return new AssignmentOption(routeIndex, order.getId(), restaurant.getId(), order.getConsumerName(), order.getConsumerLocation(), delta, resultingMakespan);
    }

    private void appendOrderToRoute(RoutePlan route,
                                    String orderId,
                                    Restaurant restaurant,
                                    String consumerName,
                                    GeoLocation consumerLocation) {
        GeoLocation current = route.getCurrentLocation();
        double t = route.getCurrentMinutes();
        double toRest = distanceService.travelMinutes(current, restaurant.getLocation());
        t += toRest;
        if (t < restaurant.getAveragePrepMinutes()) {
            double wait = restaurant.getAveragePrepMinutes() - t;
            t += wait;
            route.getSteps().add(String.format("Wait at %s for %.1f min", restaurant.getName(), wait));
        }
        route.getSteps().add(String.format("Pickup %s at %s", orderId, restaurant.getName()));
        double toConsumer = distanceService.travelMinutes(restaurant.getLocation(), consumerLocation);
        t += toConsumer;
        route.getSteps().add(String.format("Deliver %s to %s", orderId, consumerName));

        route.setCurrentLocation(consumerLocation);
        route.setCurrentMinutes(t);
    }
} 