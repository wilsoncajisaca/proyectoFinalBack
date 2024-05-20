package com.example.demo.controllers;

import com.example.demo.entities.SinisterType;
import com.example.demo.services.interfaces.ISinisterTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/sinisterType/")
public class SinisterTypeController {
    @Autowired
    private ISinisterTypeService sinisterTypeService;

    @RequestMapping("getAllSinisterType")
    public Set<SinisterType> getAllSinisterType() {
        return sinisterTypeService.getAllSinisterType();
    }
}
