package uz.xbakhromjon.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import uz.xbakhromjon.auth.request.SignInRequest;
import uz.xbakhromjon.auth.request.SignupRequest;
import uz.xbakhromjon.auth.response.JWTToken;
import uz.xbakhromjon.auth.security.DomainUserDetailsService;
import uz.xbakhromjon.auth.security.ERole;
import uz.xbakhromjon.auth.service.AuthService;
import uz.xbakhromjon.common.constants.ErrorMessages;
import uz.xbakhromjon.common.exception.BadRequestException;
import uz.xbakhromjon.common.exception.UsernameAlreadyUsedException;
import uz.xbakhromjon.user.entity.UserJpaEntity;
import uz.xbakhromjon.user.entity.UserRole;
import uz.xbakhromjon.user.mapper.UserMapper;
import uz.xbakhromjon.user.repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static uz.xbakhromjon.auth.security.SecurityUtils.AUTHORITIES_KEY;
import static uz.xbakhromjon.auth.security.SecurityUtils.JWT_ALGORITHM;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtEncoder jwtEncoder;
    @Value("${application.security.authentication.jwt.token-validity-in-seconds:0}")
    private long tokenValidityInSeconds;

    @Value("${application.security.authentication.jwt.token-validity-in-seconds-for-remember-me:0}")
    private long tokenValidityInSecondsForRememberMe;
    private final DomainUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JWTToken signIn(SignInRequest request) {
        org.springframework.security.core.userdetails.User userDetails = userDetailsService.createSpringSecurityUser(userRepository.findOneByUsername(request.getUsername()).get());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new BadRequestException(ErrorMessages.USERNAME_OR_PASSWORD_INVALID);
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.createToken(authentication, request.isRememberMe());
        return new JWTToken(jwt);
    }

    @Override
    public void signup(SignupRequest request) throws UsernameAlreadyUsedException {
        // check username is free
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyUsedException();
        }
        // map request to user
        UserJpaEntity user = userMapper.toEntity(request);

        // hash password and set to user
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        // set default role
        user.setRole(new UserRole(ERole.ROLE_USER));

        // save user
        userRepository.save(user);
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant validity;
        if (rememberMe) {
            validity = now.plus(this.tokenValidityInSecondsForRememberMe, ChronoUnit.SECONDS);
        } else {
            validity = now.plus(this.tokenValidityInSeconds, ChronoUnit.SECONDS);
        }

        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
