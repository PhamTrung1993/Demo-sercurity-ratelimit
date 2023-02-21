package com.example.demosercurityratelimit.controller;

import com.example.demosercurityratelimit.service.utility.JokeUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jokeManagement/v1")
public class JokeController {
    @GetMapping(value = "/joke", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> jokeRetreivalHandler(@RequestHeader(name = "Authorization", required = true) final String header) {
        return ResponseEntity.ok(JokeUtility.generate());
    }
}
