package com.example.demosercurityratelimit.service.plan;

import com.example.demosercurityratelimit.dto.PlanResponseDTO;
import com.example.demosercurityratelimit.repository.IPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PlanService {
    @Autowired
    private IPlanRepository planRepository;


    public ResponseEntity<List<PlanResponseDTO>> allPlansInSystemRetreivalHandler() {
        return ResponseEntity
                .ok(planRepository
                        .findAll().parallelStream().map(plan -> PlanResponseDTO.builder().id(plan.getId())
                                .name(plan.getName()).limitPerHour(plan.getLimitPerHour()).build())
                        .collect(Collectors.toList()));
    }
}
