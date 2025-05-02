package com.example.cardatabase.web;

import com.example.cardatabase.domain.LoginResponse;
import com.example.cardatabase.service.UserDetailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

     @RequestMapping("/login")
     public String login(HttpServletRequest req, HttpServletResponse res) throws IOException {
         return userDetailServiceImpl.login(req, res);
     }

}
