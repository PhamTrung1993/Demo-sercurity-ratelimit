package com.example.demosercurityratelimit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {
    private int errorCode;
    private String message;
    private Object data;
}
