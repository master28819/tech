package com.example.tech;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.cdimascio.dotenv.Dotenv;

@RestController
public class EnvController {


    private Dotenv dotenv = Dotenv.load();
    @Value("${WHATS_NAME:not working}")
    private String myEnvVariable;

    @GetMapping("/env")
    public String getEnvVariable() {
    	
    	if( myEnvVariable.equals("not working") ) {
    		myEnvVariable = dotenv.get("WHATS_NAME");
    	}
    	
        return "Value of WHATS_NAME: " + myEnvVariable;
    }
}
	