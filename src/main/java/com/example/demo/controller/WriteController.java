package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;

@RestController
public class WriteController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView list(HttpSession session) throws URISyntaxException {


        ModelAndView modelAndView = new ModelAndView();
        System.out.println(userService.getSkills(session.getAttribute("userId").toString()));
        modelAndView.addObject("skills",userService.getSkills(session.getAttribute("userId").toString()));
        // URI location = new URI("/list/"+resource.getIdx());
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping(value="/delete/{skill}", method = RequestMethod.DELETE)
    @ResponseBody
    public ModelAndView delete(@PathVariable String skill,HttpSession session) {
        userService.delete(session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
