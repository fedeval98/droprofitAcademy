package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    @Autowired
    CoursesService coursesService;
}
