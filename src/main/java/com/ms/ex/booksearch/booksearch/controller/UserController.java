package com.ms.ex.booksearch.booksearch.controller;

import com.ms.ex.booksearch.booksearch.dto.UserDto;
import com.ms.ex.booksearch.booksearch.entity.Role;
import com.ms.ex.booksearch.booksearch.entity.User;
import com.ms.ex.booksearch.booksearch.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private TokenStore tokenStore;


    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto){
        if(!userDto.getPassword().equals(userDto.getPasswordConfirmation()))
            return "passwords 가 일치하지 않습니다.";
        else if(userService.getUser(userDto.getUsername()) != null)
            return "이미 같은 사용자가 있습니다.";

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(userDto.getUsername()).find())
            return "알파벳과 숫자만 가능합니다.";

        userService.save(new User(userDto.getUsername(),
                userDto.getPassword(),
                Arrays.asList(new Role("USER"),
                        new Role("ACTUATOR"))));
        return "사용자가 생성되었습니다.";
    }

    @GetMapping("/users")
    public List<User> users(){
        return userService.getAllUsers();
    }

    @GetMapping("/logouts")
    public void logout(@RequestParam("access_token") String accessToken){
        OAuth2AccessToken token = tokenStore.readAccessToken(accessToken);
        if(token != null) tokenStore.removeAccessToken(token);
    }

    @GetMapping("/getUsername")
    public String getUsername(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }



}