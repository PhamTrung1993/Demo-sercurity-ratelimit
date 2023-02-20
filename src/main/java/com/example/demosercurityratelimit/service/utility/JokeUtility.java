package com.example.demosercurityratelimit.service.utility;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
public class JokeUtility {
    public static String generate() {
        JSONObject response = new JSONObject();
        try {
            response.put("joke", "My life.");
        } catch (JSONException e) {
            log.error("Unable to generate JSON response");
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
        return response.toString();
    }
}
