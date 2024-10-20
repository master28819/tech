package com.example.tech;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

    @Value("${WHATS_NAME:not working}")
    private String myEnvVariable;

    @GetMapping("/env")
    public String getEnvVariable() {
        return "Value of WHATS_NAME: " + myEnvVariable;
    }
}
	