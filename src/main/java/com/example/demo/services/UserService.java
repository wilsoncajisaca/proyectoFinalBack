package com.example.demo.services;

import com.example.demo.entities.Auth;
import com.example.demo.entities.RolPartner;
import com.example.demo.exception.GeneralException;
import com.example.demo.pojos.errors.ApiError;
import com.example.demo.repositories.IAuthRepository;
import com.example.demo.tools.ExceptionTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends ExceptionTool implements UserDetailsService {
    @Autowired
    IAuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth auth = authRepository.findByUsernameAndStatus(username, true)
                .orElseThrow(this::generateErrorUserPassNotFound);

        return new User(auth.getUsername(), auth.getPassword(), mappedRole(auth.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mappedRole(Set<RolPartner> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
