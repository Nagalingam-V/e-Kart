package com.example.eKart.user.controller;

import com.example.eKart.user.data.LoginData;
import com.example.eKart.user.data.UserData;
import com.example.eKart.user.service.UserService;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserApiResource {

    private final UserService userService;

    public UserApiResource(UserService userService) {
        System.out.println("USER CONTROLLER CALLED FOR ONE");
        this.userService = userService;
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<UserData> createUser(@RequestBody UserData userData){
        UserData response = userService.createUser(userData);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginData loginData){
        return ResponseEntity.ok(userService.loginUser(loginData));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Long> getUser(@PathVariable Long id){
        return ResponseEntity.ok(id);
    }
}
