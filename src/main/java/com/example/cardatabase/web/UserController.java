package com.example.cardatabase.web;

import com.example.cardatabase.domain.AccountCredentials;
import com.example.cardatabase.domain.LoginResponse;
import com.example.cardatabase.service.UserDetailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

     @RequestMapping("/login")
     public ResponseEntity<LoginResponse> login(@RequestBody AccountCredentials accountCredentials) throws IOException {
         return userDetailServiceImpl.login(accountCredentials);
     }

}
