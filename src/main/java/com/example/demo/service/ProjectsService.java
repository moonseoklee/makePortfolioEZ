package com.example.demo.service;


import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectsRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {

    private ProjectsRepository projectsRepository;
    private UserRepository userRepository;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository,UserRepository userRepository){
        this.projectsRepository = projectsRepository;
        this.userRepository = userRepository;
    }



    public void update(String userId, String title, String description,String url) {
        if(url.substring(0,4)!="http"){
            url = "https://"+url;
        }
        Project project = new Project();
        project = project.builder().userId(userId).title(title).description(description).gitUrl(url).build();
        projectsRepository.save(project);
    }

    public List<Project> getProjects(String userId){


        User user = userRepository.findById(userId).orElse(null);
        List<Project> projects = projectsRepository.findAllByUserId(userId);

        user.setProjects(projects);


        return projects;

    }

    public void deleteProject(String id) {
        projectsRepository.deleteById(Long.parseLong(id));
    }

    public Optional<Project> findProject(String id){
        return projectsRepository.findById(Long.parseLong(id));
    }

    public void modifyProject(String pId, String title, String des, String url) {
        Long id = Long.parseLong(pId);

        Project project = projectsRepository.findById(id).orElse(null);
        System.out.println(title+des+url+"aa");
        project.setDescription(des);
        project.setTitle(title);
        project.setGitUrl(url);

        projectsRepository.save(project);

    }
}
