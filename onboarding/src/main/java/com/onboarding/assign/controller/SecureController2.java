package com.onboarding.assign.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/secure")
public class SecureController2 {
	
	@Value("${hello.message}")
    private String helloMessage;

    @GetMapping
    public String secure(Principal principal) {
        return helloMessage;
    }

}
