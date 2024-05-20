package com.example.demo.services;

import com.example.demo.entities.SinisterType;
import com.example.demo.repositories.ISinisterRepository;
import com.example.demo.repositories.ISinisterTypeRepository;
import com.example.demo.services.interfaces.ISinisterTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SinisterTypeService implements ISinisterTypeService {
    @Autowired
    private ISinisterTypeRepository sinisterTypeRepository;
    @Override
    public Set<SinisterType> getAllSinisterType() {
        return sinisterTypeRepository.findAll().stream().collect(Collectors.toSet());
    }
}
