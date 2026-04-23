package com.example.optimizer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Truck {

    private String id;

    @JsonProperty("max_weight_lbs")
    private int maxWeightLbs;

    @JsonProperty("max_volume_cuft")
    private int maxVolumeCuft;

}