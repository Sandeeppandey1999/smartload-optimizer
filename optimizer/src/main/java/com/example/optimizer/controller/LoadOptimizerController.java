package com.example.optimizer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.optimizer.dto.RequestDto;
import com.example.optimizer.dto.ResponseDto;
import com.example.optimizer.service.LoadOptimizerService;

@RestController
@RequestMapping("/api/v1/load-optimizer")
public class LoadOptimizerController {

    private static final int MAX_ORDERS = 22;

    private final LoadOptimizerService service;

    public LoadOptimizerController(LoadOptimizerService service) {
        this.service = service;
    }

    @PostMapping("/optimize")
    public ResponseEntity<ResponseDto> optimize(@RequestBody RequestDto request) {
        if (request.getTruck() == null || request.getOrders() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getTruck().getMaxWeightLbs() <= 0 || request.getTruck().getMaxVolumeCuft() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getOrders().size() > MAX_ORDERS) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
        }

        ResponseDto response = service.optimize(request);
        return ResponseEntity.ok(response);
    }
}

