package com.druiz.bosonit.crudsecurity.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    @Lazy /* Avoid cyclic dependencies error */
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtAuthFilter;

    /**
     * Custom password storage (UserDetailsService)
     *
     * @return
     */
    @Bean
    UserDetailsService userDetailsService() {
        var userDt = new InMemoryUserDetailsManager();/* In-memory data-store */
        /**
         * Prefix which specifies hashing algorithm. Example:
         * - {noop}: use raw password
         * - {bcrypt}: use Bcrypt algorithm
         */
        userDt.createUser(User.builder().username("user").password("{noop}user").roles("USER").build());
        userDt.createUser(User.builder().username("admin").password("{noop}admin").roles("USER", "ADMIN").build());
        return userDt;
    }

    /**
     * Create LoginFilter (user, pass authentication)
     *
     * @param userDetailsService
     * @return
     */
    /* Expose AuthenticationManager bean */
    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        var daoAuth = new DaoAuthenticationProvider();
        daoAuth.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuth);
    }

    /**
     * Component-based security configuration. WebSecurityConfigurerAdapter is
     * deprecated since version 5.7
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilterAt(loginFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(jwtAuthFilter, BasicAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);/* Token-based authentication does not require session */

        /* Custom error message */
        http.exceptionHandling()
                /*
                 * For simple message, I use lambda instead of create new class and implement
                 * AccessDeniedHandler
                 */
                .accessDeniedHandler((request, response, accessDeniedException) -> {

                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().print(accessDeniedException.getMessage());/* Define your own message */
                }).authenticationEntryPoint((request, response, authException) -> {

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().print(authException.getMessage());/* Define your own message */

                });

        return http.build();

    }


}