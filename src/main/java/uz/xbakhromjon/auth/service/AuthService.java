package uz.xbakhromjon.auth.service;

import uz.xbakhromjon.auth.request.SignInRequest;
import uz.xbakhromjon.auth.request.SignupRequest;
import uz.xbakhromjon.auth.response.JWTToken;
import uz.xbakhromjon.auth.service.impl.AuthServiceImpl;
import uz.xbakhromjon.common.exception.UsernameAlreadyUsedException;

public interface AuthService {
    JWTToken signIn(SignInRequest request);

    void signup(SignupRequest request) throws UsernameAlreadyUsedException;
}
