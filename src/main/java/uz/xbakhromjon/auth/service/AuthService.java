package uz.xbakhromjon.auth.service;

import uz.xbakhromjon.auth.request.SignInRequest;
import uz.xbakhromjon.auth.request.SignupRequest;

public interface AuthService {
    void signIn(SignInRequest request);

    void signup(SignupRequest request);
}
