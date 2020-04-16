package com.example.demo.service;

import com.example.demo.domain.ProjectsRepository;
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
    private ProjectsRepository projectsRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProjectsRepository projectsRepository){
        this.projectsRepository = projectsRepository;
        this.userRepository = userRepository;
    }


    public Optional<User> findById(String userId){
        User user = userRepository.findById(userId).orElse(null);
        user.setProjects(projectsRepository.findAllByUserId(userId));
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
        List<String> Frs =  user.getFrameworks();
        List<String> Etcs =  user.getEtcs();
        ArrayList<List> arr = new ArrayList<List>();
        arr.add(PLs);
        arr.add(Dbs);
        arr.add(Frs);
        arr.add(Etcs);
        return arr;
    }
    public String getName(String userId) {
        User user= userRepository.findById(userId).orElse(null);

        return user.getName();
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
        }else if(category=="fr"){
            skills.get(2).remove(skill);

            user.setFrameworks(skills.get(2));
        }else if(category=="etc"){
            skills.get(3).remove(skill);

            user.setEtcs(skills.get(3));
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
    public void updateName(String name,String userId){
        User user= userRepository.findById(userId).orElse(null);
        user.setName(name);
        userRepository.save(user);
    }
    public void update(String category,String userId, String skill) {
        ArrayList<List> skills = getSkills(userId);
        User user= userRepository.findById(userId).orElse(null);
        System.out.println(skills+skill+category);
        if(category=="pls") {
            skills.get(0).add(skill);
            user.setPLS(skills.get(0));
        }else if(category=="dbs") {
            skills.get(1).add(skill);
            user.setDbs(skills.get(1));
        }else if(category=="frs") {
            skills.get(2).add(skill);
            user.setFrameworks(skills.get(2));
        }else if(category=="etc") {
            skills.get(3).add(skill);
            user.setEtcs(skills.get(3));
        }


        userRepository.save(user);

    }

    public void updateDescription(String des, String userId) {
        User user = userRepository.findById(userId).orElse(null);
        user.setDescription(des);

        userRepository.save(user);

    }

    public String getDescription(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        return user.getDescription();
    }
}
