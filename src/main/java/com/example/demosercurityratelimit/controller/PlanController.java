package com.example.demosercurityratelimit.controller;

import com.example.demosercurityratelimit.dto.PlanResponseDTO;
import com.example.demosercurityratelimit.service.plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping(value = "/plans", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<PlanResponseDTO>> allPlansInSystemRetreivalHandler() {
        return planService.allPlansInSystemRetreivalHandler();
    }
}
