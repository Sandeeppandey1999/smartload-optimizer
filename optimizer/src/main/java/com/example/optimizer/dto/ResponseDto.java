package com.example.optimizer.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto {

    @JsonProperty("truck_id")
    private String truck_id;

    @JsonProperty("selected_order_ids")
    private List<String> selected_order_ids;

    @JsonProperty("total_payout_cents")
    private long total_payout_cents;

    @JsonProperty("total_weight_lbs")
    private int total_weight_lbs;

    @JsonProperty("total_volume_cuft")
    private int total_volume_cuft;

    @JsonProperty("utilization_weight_percent")
    private double utilization_weight_percent;

    @JsonProperty("utilization_volume_percent")
    private double utilization_volume_percent;

    public ResponseDto(String truck_id, List<String> selected_order_ids, long total_payout_cents, int total_weight_lbs,
            int total_volume_cuft, double utilization_weight_percent, double utilization_volume_percent) {
        this.truck_id = truck_id;
        this.selected_order_ids = selected_order_ids;
        this.total_payout_cents = total_payout_cents;
        this.total_weight_lbs = total_weight_lbs;
        this.total_volume_cuft = total_volume_cuft;
        this.utilization_weight_percent = utilization_weight_percent;
        this.utilization_volume_percent = utilization_volume_percent;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public List<String> getSelected_order_ids() {
        return selected_order_ids;
    }

    public long getTotal_payout_cents() {
        return total_payout_cents;
    }

    public int getTotal_weight_lbs() {
        return total_weight_lbs;
    }

    public int getTotal_volume_cuft() {
        return total_volume_cuft;
    }

    public double getUtilization_weight_percent() {
        return utilization_weight_percent;
    }

    public double getUtilization_volume_percent() {
        return utilization_volume_percent;
    }
}

