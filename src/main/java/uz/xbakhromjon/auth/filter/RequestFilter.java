package uz.xbakhromjon.auth.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.xbakhromjon.auth.security.DomainUserDetailsService;
import uz.xbakhromjon.common.configuration.SecurityConfiguration;
import uz.xbakhromjon.common.constants.ErrorMessages;
import uz.xbakhromjon.common.exception.ProblemDetail;
import uz.xbakhromjon.user.entity.UserJpaEntity;
import uz.xbakhromjon.user.repository.UserRepository;

import java.io.IOException;
import java.util.Map;


public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DomainUserDetailsService domainUserDetailsService;

    /**
     * Forwards any unmapped paths (except those containing a period) to the client {@code index.html}.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer")) {
            sendError(request, response);
            return;
        }
        token = token.substring(7);
        Jwt decoded = jwtDecoder.decode(token);
        String username = decoded.getSubject();

        UserJpaEntity user = userRepository.findOneWithAuthoritiesByUsername(username).orElseThrow();

        User userDetails = domainUserDetailsService.createSpringSecurityUser(user);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return isUnsecuredEndpoint(request);
    }

    private void sendError(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        ProblemDetail problemDetail = ProblemDetail.builder().status(HttpStatus.UNAUTHORIZED.value())
                .type("UnauthorizedException")
                .details(ErrorMessages.UNAUTHORIZED)
                .path(request.getRequestURI())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.getWriter().print(objectMapper.writeValueAsString(problemDetail));
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    private boolean isUnsecuredEndpoint(HttpServletRequest request) {
        for (Map.Entry<String, HttpMethod> entry : SecurityConfiguration.UNSECURED_ENDPOINTS.entrySet()) {
            if (new AntPathMatcher().match(entry.getKey(), request.getServletPath()) && entry.getValue().name().equals(request.getMethod())) {
                return true;
            }
        }
        return false;
    }
}
