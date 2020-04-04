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

    public List<String> getPLS(String userId) {


        User user= userRepository.findById(userId).orElse(null);
        List<String> PLS =  user.getPLS();
        return PLS;
    }

    public void delete(String userId, String skill) {
        List<String> pls = getPLS(userId);
        pls.remove(skill);
        User user= userRepository.findById(userId).orElse(null);
        user.setPLS(pls);
        userRepository.save(user);
    }

    public void deleteAll(String userId) {
        List<String> pls = getPLS(userId);
        pls.clear();
        User user= userRepository.findById(userId).orElse(null);
        user.setPLS(pls);
        userRepository.save(user);
    }

    public void update(String userId, String skill) {
        List<String> pls = getPLS(userId);
        pls.add(skill);
        User user= userRepository.findById(userId).orElse(null);
        user.setPLS(pls);
        userRepository.save(user);
        System.out.println(user.getPLS());
    }
}
