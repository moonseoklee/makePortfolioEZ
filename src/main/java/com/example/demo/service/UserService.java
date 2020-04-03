package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public Optional<User> findById(String userId){

        return userRepository.findById(userId);
    }
    public User addUser(String userId) {
        User user = User.builder().id(userId).email("aa").build();

        System.out.println("adduser"+user);
        return userRepository.save(user);
    }
}
