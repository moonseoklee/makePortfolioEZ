package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


        return userRepository.save(user);
    }

    public ArrayList<List> getSkills(String userId) {
        User user= userRepository.findById(userId).orElse(null);
        List<String> PLs =  user.getPLS();
        List<String> Dbs =  user.getDbs();
        ArrayList<List> arr = new ArrayList<List>();
        arr.add(PLs);
        arr.add(Dbs);
        return arr;
    }

    public void delete(String category,String userId, String skill) {
        ArrayList<List> skills = getSkills(userId);
        User user = userRepository.findById(userId).orElse(null);
        if(category=="pl") {
            skills.get(0).remove(skill);

            user.setPLS(skills.get(0));
        }else if(category=="db"){
            skills.get(1).remove(skill);

            user.setDbs(skills.get(1));
        }
        userRepository.save(user);
    }

    public void deleteAll(String userId) {
        ArrayList<List> skills = getSkills(userId);
        skills.get(0).clear();
        User user= userRepository.findById(userId).orElse(null);
        user.setPLS(skills.get(0));
        userRepository.save(user);
    }

    public void update(String userId, String skill) {
        ArrayList<List> skills = getSkills(userId);
        skills.get(0).add(skill);
        User user= userRepository.findById(userId).orElse(null);
        user.setPLS(skills.get(0));
        userRepository.save(user);

    }
}
