package com.example.demosercurityratelimit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class PlanResponseDTO {
    private final Long id;
    private final String name;
    private final Integer limitPerHour;
}
