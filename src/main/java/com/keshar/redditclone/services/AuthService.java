package com.keshar.redditclone.services;

import com.keshar.redditclone.model.dtos.AuthenticationResponse;
import com.keshar.redditclone.model.dtos.LoginRequest;
import com.keshar.redditclone.model.dtos.RegisterRequest;

public interface AuthService {

    void signup(RegisterRequest registerRequest);

    void verifyAccount(String token);

    AuthenticationResponse login(LoginRequest loginRequest);
}
