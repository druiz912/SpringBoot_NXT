package com.druiz.bosonit.crudsecurity.security;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class LoginFilter  extends OncePerRequestFilter{

    private final AuthenticationManager authenticationManager;
    private final JwtAuth jwtAuthHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var username = request.getHeader("username");
        var password = request.getHeader("password");

        var authenticated = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(username, password)

        );
        /**
         * Example value to test login is OK, jwt-token will be added later
         * response.setHeader(HttpHeaders.AUTHORIZATION, username);
         */
        //response.setHeader(HttpHeaders.AUTHORIZATION, username);

        /* Add jwt-token for login */
        response.setHeader(HttpHeaders.AUTHORIZATION, generateJwtAuthToken(authenticated));
    }

    private String generateJwtAuthToken(Authentication authentication) {

        var user = (User) authentication.getPrincipal();
        var roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));


        return jwtAuthHelper.generateJwtAuthToken(user.getUsername(), Map.of("roles", roles));
    }


    /**
     * Make sure this filter only with login request.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        var reqMethod = request.getMethod();
        var reqUri = request.getRequestURI();
        var isLogin = HttpMethod.POST.matches(reqMethod) && reqUri.startsWith("/login");

        return !isLogin;
    }

}
