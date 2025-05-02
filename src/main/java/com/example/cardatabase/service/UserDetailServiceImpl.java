package com.example.cardatabase.service;

import com.example.cardatabase.domain.User;
import com.example.cardatabase.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRoles().toArray(new String[0]))
//                .build();
//    }
}
