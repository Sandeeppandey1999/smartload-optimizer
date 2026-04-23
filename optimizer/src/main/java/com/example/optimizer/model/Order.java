package com.example.optimizer.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Order {

    private String id;

    @JsonProperty("payout_cents")
    private long payoutCents;

    @JsonProperty("weight_lbs")
    private int weightLbs;

    @JsonProperty("volume_cuft")
    private int volumeCuft;

    private String origin;
    private String destination;

    @JsonProperty("pickup_date")
    private LocalDate pickupDate;

    @JsonProperty("delivery_date")
    private LocalDate deliveryDate;

    @JsonProperty("is_hazmat")
    private boolean hazmat;
}

