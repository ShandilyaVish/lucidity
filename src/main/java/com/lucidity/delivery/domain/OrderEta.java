package com.lucidity.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEta {
    private String orderId;
    private String consumerName;
    private double etaMinutes;
} 