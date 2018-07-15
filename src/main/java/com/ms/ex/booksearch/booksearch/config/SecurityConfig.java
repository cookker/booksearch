package com.ms.ex.booksearch.booksearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  Environment environment;

    private boolean isLocal() {
        return Arrays.stream(environment.getActiveProfiles()).anyMatch(v -> "local".equals(v) || "default".equals(v));
    }

    private void setLocalMode(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/login/**",
                        "/js/**",
                        "/css/**",
                        "/image/**",
                        "/fonts/**",
                        "/favicon.ico").permitAll()
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(isLocal()){
            setLocalMode(http);
        }else {
            http
                    .authorizeRequests()
                    .antMatchers(
                            "/", "/home", "/register", "/login", "/h2-console/**", "/getUsername"
                    ).permitAll()
                    .antMatchers("/oauth/**").hasAuthority("USER")
                    .antMatchers("/books/**").hasAuthority("USER")
                    .anyRequest().authenticated();
        }
    }
}
