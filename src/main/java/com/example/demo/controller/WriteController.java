package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.URISyntaxException;

@Controller
public class WriteController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView list(HttpSession session) throws URISyntaxException {


        ModelAndView modelAndView = new ModelAndView();
        //System.out.println(userService.getSkills(session.getAttribute("userId").toString()).get(0));
        //System.out.println(userService.getPLS(session.getAttribute("userId").toString()));
        modelAndView.clear();

        modelAndView.addObject("pls",userService.getSkills(session.getAttribute("userId").toString()).get(0));
        modelAndView.addObject("dbs",userService.getSkills(session.getAttribute("userId").toString()).get(1));
        modelAndView.addObject("frs",userService.getSkills(session.getAttribute("userId").toString()).get(2));
        modelAndView.addObject("etc",userService.getSkills(session.getAttribute("userId").toString()).get(3));
        modelAndView.addObject("name",userService.getName(session.getAttribute("userId").toString()));
        modelAndView.addObject("des",userService.getDescription(session.getAttribute("userId").toString()));
        //modelAndView.addObject("etcs",userService.getSkills(session.getAttribute("userId").toString()).get(3));
        System.out.println(modelAndView.getModel());
        // URI location = new URI("/list/"+resource.getIdx());
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @RequestMapping(value="/delete/pl/{skill}", method = RequestMethod.POST)
    public String deletepl(@PathVariable String skill,HttpSession session) throws URISyntaxException {
        userService.delete("pl",session.getAttribute("userId").toString(),skill);

        return "redirect:/home";
    }
    @RequestMapping(value="/delete/db/{skill}", method = RequestMethod.POST)
    public String deletedb(@PathVariable String skill,HttpSession session) throws URISyntaxException {
        userService.delete("db",session.getAttribute("userId").toString(),skill);

        return "redirect:/home";
    }
    @RequestMapping(value="/delete/framework/{skill}", method = RequestMethod.POST)
    public String deleteFramework(@PathVariable String skill,HttpSession session) throws URISyntaxException {
        userService.delete("fr",session.getAttribute("userId").toString(),skill);

        return "redirect:/home";
    }
    @RequestMapping(value="/delete/etc/{skill}", method = RequestMethod.POST)
    public String deleteEtc(@PathVariable String skill,HttpSession session) throws URISyntaxException {
        userService.delete("etc",session.getAttribute("userId").toString(),skill);

        return "redirect:/home";
    }
    @RequestMapping(value="/deleteAllpl", method = RequestMethod.POST)
    public String deleteAll(HttpSession session) throws URISyntaxException {
        userService.deleteAll(session.getAttribute("userId").toString());

        return"redirect:/home";
    }
    @RequestMapping(value="/updatepl", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updatepl(HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String skill = request.getParameter("data");

        userService.update("pls",session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);

        return modelAndView;
    }
    @RequestMapping(value="/updatedb", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updatedb(HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String skill = request.getParameter("data");

        userService.update("dbs",session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);

        return modelAndView;
    }
    @RequestMapping(value="/updatefr", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updatefr(HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String skill = request.getParameter("data");

        userService.update("frs",session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);

        return modelAndView;
    }
    @RequestMapping(value="/updateetc", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateetc(HttpServletRequest request, HttpSession session) throws URISyntaxException {
        String skill = request.getParameter("data");

        userService.update("etc",session.getAttribute("userId").toString(),skill);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);

        return modelAndView;
    }
    @RequestMapping(value="/updatename", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updatename(@RequestParam("name")String name, HttpSession session) throws URISyntaxException {


        userService.updateName(name,session.getAttribute("userId").toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);

        return modelAndView;
    }

    @RequestMapping(value="/updatedescription", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updatedescription(@RequestParam("des")String des, HttpSession session) throws URISyntaxException {


        userService.updateDescription(des,session.getAttribute("userId").toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = list(session);

        return modelAndView;
    }
}
