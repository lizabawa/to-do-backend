package com.example.todobackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/auth/users/", "/auth/users/login/", "/auth/users/register/").permitAll()
                .antMatchers("/h2-console/**").permitAll()  //DO NOT NEED THIS LINE IN PRODUCTION
                .anyRequest().authenticated() //any request, must be authenticated
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //store session data on client side
                .and().csrf().disable()//filters the traffic by disabling all access "cross site request forgery"
                .headers().frameOptions().disable(); //without this line the ui will not be framed together. DO NOT NEED THIS LINE IN PRODUCTION
        http.cors(); //Enable CORS
        http.addFilterBefore(authJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); //calls the method we created
        return http.build(); //return the http
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
}
