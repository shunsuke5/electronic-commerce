package com.example.ecsite.controller;

import com.example.ecsite.jwt.TokenSigner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
public class TokenController {

    private final TokenSigner tokenSigner;
    private final AuthenticationManager manager;
    private final Clock clock;
}
