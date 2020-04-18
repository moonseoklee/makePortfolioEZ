package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.service.ProjectsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;

@Controller
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    @Autowired
    WriteController writeController;


    @RequestMapping(value = "/updateproject", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView updateProject(HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String title = request.getParameter("projectTitle");
        String description = request.getParameter("projectDescription");
        String gitUrl = request.getParameter("projectUrl");
        projectsService.update(session.getAttribute("userId").toString(),title,description,gitUrl);

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
