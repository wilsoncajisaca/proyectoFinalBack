package com.example.demo.tools;

import com.example.demo.commons.Commons;
import com.example.demo.entities.Auth;
import com.example.demo.entities.Partner;
import com.example.demo.entities.RolPartner;
import com.example.demo.exception.GeneralException;
import com.example.demo.mappers.AuthRoleMapper;
import com.example.demo.pojos.errors.ApiError;
import com.example.demo.repositories.IRolePartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class PartnerTool extends ExceptionTool {
    private final String ROL_EMPLOYEE = "ROLE_USER";
    @Autowired
    private IRolePartnerRepository rolRepository;
    protected String createAuthPassword(){
        return Commons.createAleatoryPassword();
    }

    protected Set<Auth> createAuth(Partner partner, String password) {
        Auth auth = AuthRoleMapper.toEntity(partner.getPartnerId(), partner.getIdentification(), password);
        RolPartner role = rolRepository.findByName(ROL_EMPLOYEE).orElseThrow(this::generalErrorNotFoundRole);
        auth.setRoles(Collections.singleton(role));
        Set<Auth> auths = new HashSet<>();
        auths.add(auth);
        return auths;
    }
}
