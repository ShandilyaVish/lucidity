package com.lucidity.delivery.service;

import com.lucidity.delivery.domain.BatchPlan;
import com.lucidity.delivery.domain.DeliveryPerson;
import com.lucidity.delivery.domain.Order;

import java.util.List;

public interface BatchOptimizationService {
    BatchPlan computeOptimalPlan(DeliveryPerson deliveryPerson, List<Order> orders);

    BatchPlan computeOptimalPlan(List<DeliveryPerson> deliveryPeople, List<Order> orders);
} 