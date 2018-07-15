package com.ms.ex.booksearch.booksearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/home","/register","/login").permitAll()
                .antMatchers("/books/**").authenticated()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/bookform","/books").authenticated()
                .antMatchers(HttpMethod.DELETE , "/books/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().access("#oauth2.hasScope('read')");
    }


}