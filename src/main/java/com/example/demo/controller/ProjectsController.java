package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.service.ProjectsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    @Autowired
    WriteController writeController;


    @RequestMapping(value = "/updateproject", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView updateProject(@RequestParam("projectImg") MultipartFile file, HttpServletRequest request, HttpSession session) throws URISyntaxException, IOException {
        String title = request.getParameter("projectTitle");
        String description = request.getParameter("projectDescription");
        String gitUrl = request.getParameter("projectUrl");


        String path2;
        System.out.println(file);
        if(!file.isEmpty()) {

            byte[] bytes = file.getBytes();
            Path path = Paths.get("./src/main/resources/static/img/" + file.getOriginalFilename());
            Files.write(path, bytes);
            path2 = "/img/" + file.getOriginalFilename();
        }else{
            path2 = "";
        }
        projectsService.update(session.getAttribute("userId").toString(),title,description,gitUrl,path2);



        ModelAndView modelAndView;
        modelAndView = writeController.list(session);

        return modelAndView;

    }

    @RequestMapping(value="/modifyproject",method=RequestMethod.POST)
    public String modifyProject(HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String id = request.getParameter("id");
        String title = request.getParameter("projectTitle");
        String des = request.getParameter("projectDescription");
        String url = request.getParameter("projectUrl");
        projectsService.modifyProject(id,title,des,url);

        return "redirect:/project?id="+id;
    }

    @RequestMapping(value="/deleteproject", method = RequestMethod.POST)
    public String deleteProject(HttpServletRequest request,HttpSession session) throws URISyntaxException {
        projectsService.deleteProject(request.getParameter("id"));

        return"redirect:/home";
    }

    @RequestMapping(value="/project", method = RequestMethod.GET)
    public ModelAndView project(HttpServletRequest request,HttpSession session) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView();

        Project project = projectsService.findProject(request.getParameter("id")).orElse(null);

        modelAndView.addObject("title",project.getTitle());
        modelAndView.addObject("id",project.getId());
        modelAndView.addObject("description",project.getDescription());
        modelAndView.addObject("url",project.getGitUrl());

        modelAndView.setViewName("/project");

        return modelAndView;
    }
}
