package com.example.demo.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${jwt.signing-key:HT3zw6GeLg3T/4g5ogH2ACyb4AWKEVvxvJ/rHK9Dmyzg2B5c3dWp9Ip/Nxnehf5Itmye0AldsEElqQ/jaXM7QA==}")
    public String SIGNING_KEY;

    public String getSIGNING_KEY() {
        return this.SIGNING_KEY;
    }

    public void setSIGNING_KEY(String SIGNING_KEY) {
        this.SIGNING_KEY = SIGNING_KEY;
    }
}
