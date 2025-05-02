package com.example.cardatabase.service;

import com.example.cardatabase.LoginFilter;
import com.example.cardatabase.domain.LoginResponse;
import com.example.cardatabase.domain.User;
import com.example.cardatabase.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException
    {
        User currentUser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core
                .userdetails.User(username, currentUser.getPassword()
                , true, true, true, true,
                AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }
    public LoginResponse login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = repository.findByUsername(username);
        if (user == null) {
            return new LoginResponse("User not found");
        }
        if (!password.equals(user.getPassword())) {
            return new LoginResponse("PASSWORD NOT MATCH");
        }
        String token = AuthenticationService.addToken(res, username).toString();
        return new LoginResponse(token, user.getUsername(), user.getRole());

    }



}
