package com.example.demo.service;


import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsService {

    private ProjectsRepository projectsRepository;

    public void update(String userId, String title, String description) {

    }

    public ArrayList<List> getProjects(String userId){
        List<Project> projects = projectsRepository.findAllByUserId(userId);




    }
}
