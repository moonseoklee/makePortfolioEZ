package com.example.demo.controller;

import com.example.demo.service.ProjectsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

        projectsService.update(session.getAttribute("userId").toString(),title,description);

        ModelAndView modelAndView;
        modelAndView = writeController.list(session);

        return modelAndView;

    }
    @RequestMapping(value="/deleteproject", method = RequestMethod.POST)
    public String deleteProject(HttpServletRequest request,HttpSession session) throws URISyntaxException {
        projectsService.deleteProject(request.getParameter("id"));

        return"redirect:/home";
    }
}
