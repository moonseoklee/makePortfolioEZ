package com.example.demo.service;


import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectsRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsService {

    private ProjectsRepository projectsRepository;
    private UserRepository userRepository;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository,UserRepository userRepository){
        this.projectsRepository = projectsRepository;
        this.userRepository = userRepository;
    }



    public void update(String userId, String title, String description) {
        System.out.println(title+description);
        Project project = new Project();
        project = project.builder().userId(userId).title(title).description(description).build();
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
}
