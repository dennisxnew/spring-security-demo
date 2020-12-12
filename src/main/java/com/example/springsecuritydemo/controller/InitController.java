package com.example.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class InitController {

    @RequestMapping("/hello")
    public Object hello(){
        String result = "hello";


        return result;
    }

    @RequestMapping("/expired")
    public Object expired(){
        String result = "expired session";
        

        return result;
    }

    @RequestMapping("/invalid")
    public Object invalid(){
        String result = "invalid session";


        return result;
    }

    @RequestMapping("/test/getName")
    public Object getName(HttpServletRequest request){
        HttpSession session = request.getSession();
        String result = "";
        result = (String) session.getAttribute("session") != null ? (String) session.getAttribute("session") : "no session";


        return result;
    }

    @RequestMapping("/test/setSession")
    public Object setSession(HttpServletRequest request){
        String result = "setSession";
        HttpSession session = request.getSession();
        session.setAttribute("session", "Dennis");
        session.setMaxInactiveInterval(5);

        return result;
    }

    @RequestMapping("performlogin")
    public Object performlogin(){
        return "performlogin";
    }
}
