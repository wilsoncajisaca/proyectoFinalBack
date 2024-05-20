package com.example.demo.mappers;

import com.example.demo.entities.Auth;

import java.util.UUID;

public class AuthRoleMapper {
    public static Auth toEntity(UUID partnerId, String userName, String password){
        return Auth.builder()
                .partnerId(partnerId)
                .username(userName)
                .password(password)
                .build();
    }
}
