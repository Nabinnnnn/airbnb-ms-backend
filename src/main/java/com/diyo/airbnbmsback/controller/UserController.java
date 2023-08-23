package com.diyo.airbnbmsback.controller;

import com.diyo.airbnbmsback.entity.User;
import com.diyo.airbnbmsback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody User user){
        userService.signUp(user);

        return ResponseEntity.ok("Success with id "+user.getId());
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        String message=userService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(message);
    }
}
