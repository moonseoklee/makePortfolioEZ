package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<String> getSkills(String userId) {


        User user= userRepository.findById(userId).orElse(null);
        List<String> skills =  user.getSkills();
        return skills;
    }

    public void delete(String userId, String skill) {
        User user= userRepository.findById(userId).orElse(null);
        List<String> skills =  user.getSkills();
    }
}
