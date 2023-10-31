package uz.xbakhromjon.auth.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.xbakhromjon.auth.request.SignInRequest;
import uz.xbakhromjon.auth.request.SignupRequest;
import uz.xbakhromjon.auth.response.JWTToken;
import uz.xbakhromjon.auth.security.DomainUserDetailsService;
import uz.xbakhromjon.auth.service.AuthService;
import uz.xbakhromjon.common.exception.UsernameAlreadyUsedException;
import uz.xbakhromjon.user.repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static uz.xbakhromjon.auth.security.SecurityUtils.AUTHORITIES_KEY;
import static uz.xbakhromjon.auth.security.SecurityUtils.JWT_ALGORITHM;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignupRequest signupRequest) throws UsernameAlreadyUsedException {
        log.info("REST request to signup: {}", signupRequest.toString());
        authService.signup(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody SignInRequest signInRequest) {
        log.info("REST request to create user: {}", signInRequest.toString());
        JWTToken token = authService.signIn(signInRequest);
        return ResponseEntity.ok(token);
    }

}
