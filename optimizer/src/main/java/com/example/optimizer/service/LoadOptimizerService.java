package com.example.optimizer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.optimizer.dto.RequestDto;
import com.example.optimizer.dto.ResponseDto;
import com.example.optimizer.model.Order;
import com.example.optimizer.model.Truck;

@Service
public class LoadOptimizerService {

    public ResponseDto optimize(RequestDto request) {
        Truck truck = request.getTruck();
        List<Order> orders = request.getOrders();

        int n = orders.size();

        long bestPayout = 0;
        int bestWeight = 0;
        int bestVolume = 0;
        List<String> bestOrders = new ArrayList<>();

        // Bitmask DP over all subsets — O(n * 2^n), feasible for n ≤ 22
        for (long mask = 0; mask < (1L << n); mask++) {

            long totalPayout = 0;
            int totalWeight = 0;
            int totalVolume = 0;
            List<Order> selected = new ArrayList<>();

            Boolean hazmat = null;
            String origin = null;
            String destination = null;

            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1L << i)) != 0) {

                    Order o = orders.get(i);

                    // weight/volume accumulate — prune early
                    totalWeight += o.getWeightLbs();
                    totalVolume += o.getVolumeCuft();

                    if (totalWeight > truck.getMaxWeightLbs()
                            || totalVolume > truck.getMaxVolumeCuft()) {
                        valid = false;
                        break;
                    }

                    // route compatibility
                    if (origin == null) {
                        origin = o.getOrigin();
                        destination = o.getDestination();
                    } else {
                        if (!origin.equals(o.getOrigin())
                                || !destination.equals(o.getDestination())) {
                            valid = false;
                            break;
                        }
                    }

                    // time-window check: pickup must not be after delivery
                    if (o.getPickupDate().isAfter(o.getDeliveryDate())) {
                        valid = false;
                        break;
                    }

                    // hazmat isolation: cannot mix hazmat and non-hazmat orders
                    if (hazmat == null) {
                        hazmat = o.isHazmat();
                    } else if (hazmat != o.isHazmat()) {
                        valid = false;
                        break;
                    }

                    totalPayout += o.getPayoutCents();
                    selected.add(o);
                }
            }

            if (!valid) continue;

            if (totalPayout > bestPayout) {
                bestPayout = totalPayout;
                bestWeight = totalWeight;
                bestVolume = totalVolume;
                bestOrders = selected.stream()
                        .map(Order::getId)
                        .toList();
            }
        }

        double weightUtil = Math.round((bestWeight * 100.0 / truck.getMaxWeightLbs()) * 100.0) / 100.0;
        double volumeUtil = Math.round((bestVolume * 100.0 / truck.getMaxVolumeCuft()) * 100.0) / 100.0;

        return new ResponseDto(
                truck.getId(),
                bestOrders,
                bestPayout,
                bestWeight,
                bestVolume,
                weightUtil,
                volumeUtil
        );
    }
}