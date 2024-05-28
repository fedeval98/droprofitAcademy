package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("All")
    public Set<CoursesDTO> getAllCourses() {
        return coursesService.getAllCoursesDTO();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCourse(@RequestBody CreateCourse createCourse) {
        return coursesService.createCourse(createCourse);
    }

    @PatchMapping("/delete")
    public ResponseEntity<String> deleteCourses(
           @RequestBody CoursesDTO coursesDTO) {

        return coursesService.deleteCourse(coursesDTO.getId());

    }
}

