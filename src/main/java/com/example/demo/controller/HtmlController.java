package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;

@Controller
public class HtmlController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sample", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView sample(HttpSession session){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",session.getAttribute("userId").toString());
        modelAndView.addObject("skills",userService.getPLS(session.getAttribute("userId").toString()));
        modelAndView.setViewName("/portfolio");
        return modelAndView;
    }

}
