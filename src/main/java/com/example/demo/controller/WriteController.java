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
        //System.out.println(userService.getPLS(session.getAttribute("userId").toString()));
        modelAndView.addObject("skills",userService.getPLS(session.getAttribute("userId").toString()));
        // URI location = new URI("/list/"+resource.getIdx());
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping(value="/delete/{skill}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView delete(@PathVariable String skill,HttpSession session) throws URISyntaxException {
        userService.delete(session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);
        return modelAndView;
    }
    @RequestMapping(value="/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteAll(HttpSession session) throws URISyntaxException {
        userService.deleteAll(session.getAttribute("userId").toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);
        return modelAndView;
    }
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView update(@RequestParam String skill,HttpSession session) throws URISyntaxException {
        userService.update(session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);
        return modelAndView;
    }
}
