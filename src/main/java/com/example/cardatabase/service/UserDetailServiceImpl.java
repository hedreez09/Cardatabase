package com.example.cardatabase.service;

import com.example.cardatabase.LoginFilter;
import com.example.cardatabase.domain.AccountCredentials;
import com.example.cardatabase.domain.LoginResponse;
import com.example.cardatabase.domain.User;
import com.example.cardatabase.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public ResponseEntity<LoginResponse> login(AccountCredentials accountCredentials) throws IOException {

        User user = repository.findByUsername(accountCredentials.getUsername());
        if (user == null) {
            return ResponseEntity.badRequest().body(new LoginResponse("User not found"));
        }

        if (!new BCryptPasswordEncoder(12).matches(accountCredentials.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new LoginResponse("PASSWORD NOT MATCH"));
        }
        String token = AuthenticationService.addToken(accountCredentials.getUsername()).toString();
        return ResponseEntity.ok(new LoginResponse(token, user.getUsername(), user.getRole()));

    }



}
