package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.URISyntaxException;

@RestController
public class WriteController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView list(HttpSession session) throws URISyntaxException {


        ModelAndView modelAndView = new ModelAndView();
        //System.out.println(userService.getSkills(session.getAttribute("userId").toString()).get(0));
        //System.out.println(userService.getPLS(session.getAttribute("userId").toString()));
        modelAndView.clear();

        modelAndView.addObject("skills",userService.getSkills(session.getAttribute("userId").toString()).get(0));
        modelAndView.addObject("dbs",userService.getSkills(session.getAttribute("userId").toString()).get(1));
        System.out.println(modelAndView.getModel());
        // URI location = new URI("/list/"+resource.getIdx());
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping(value="/delete/pl/{skill}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deletepl(@PathVariable String skill,HttpSession session) throws URISyntaxException {
        userService.delete("pl",session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);
        return modelAndView;
    }
    @RequestMapping(value="/delete/db/{skill}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deletedb(@PathVariable String skill,HttpSession session) throws URISyntaxException {
        userService.delete("db",session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);
        return modelAndView;
    }
    @RequestMapping(value="/deleteAllpl", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteAll(HttpSession session) throws URISyntaxException {
        userService.deleteAll(session.getAttribute("userId").toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);
        return modelAndView;
    }
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView update(HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String skill = request.getParameter("data");

        userService.update(session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);

        return modelAndView;
    }
}
