package com.yapr.dataservice.javaflavor.web;

import com.yapr.dataservice.javaflavor.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExtraController {
    private SampleService sampleService;

    @Autowired
    public ExtraController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/collections")
    public Collection<String> collections() {
        return sampleService.getDataCollectionNames();
    }



}

