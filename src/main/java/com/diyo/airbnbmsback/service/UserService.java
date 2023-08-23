package com.diyo.airbnbmsback.service;

import com.diyo.airbnbmsback.entity.User;
import com.diyo.airbnbmsback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void signUp(User user) {
        userRepository.save(user);


    }

    public String login(String email, String password) {
        Optional<User> user=userRepository.findByEmailAndPassword(email,password);
        if(user.isPresent()){
            return "Success";
        }
        else{
            return "Invalid email or password";
        }


    }
}
