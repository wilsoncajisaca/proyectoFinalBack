package com.example.demo.services;

import com.example.demo.entities.Auth;
import com.example.demo.entities.Partner;
import com.example.demo.pojos.inputs.AuthINP;
import com.example.demo.repositories.IAuthRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.services.interfaces.IAuthService;
import com.example.demo.services.interfaces.IPartnerService;
import com.example.demo.tools.ExceptionTool;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthService extends ExceptionTool implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IAuthRepository authRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private IPartnerService partnerService;

    @Override
    public Map<String,Object> autenticate(AuthINP auth) throws Exception {
        SecurityContextHolder.getContext().setAuthentication(this.authenticateUser(auth));
        //obtenemos el token del jwtTokenProvider
        String token = jwtTokenProvider.generateToken(auth.getUsername(), 90200);
        Partner partner = partnerService.getByIdentification(auth.getUsername());
        Map<String,Object> res = new LinkedHashMap<>();
        res.put("usuario", partner.getNames() + " " + partner.getLastname());
        res.put("token", token);
        return res;
    }

    private Authentication authenticateUser(AuthINP auth) throws AuthException {
        try {
            Auth authOpt = authRepository.findByUsername(auth.getUsername())
                    .orElseThrow(this::generateErrorUserPassNotFound);
            if(!authOpt.getStatus()) {
                throw userNotAvalaible();
            }
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        }catch(BadCredentialsException e) {
            throw generateErrorUserPassNotFound();
        }catch(UsernameNotFoundException user) {
            throw generateErrorUserPassNotFound();
        }
    }
}
