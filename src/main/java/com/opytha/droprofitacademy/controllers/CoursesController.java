package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/courses")
    public Set<CoursesDTO> getAllCourses() {
        return coursesService.getAllCoursesDTO();
    }

    @PostMapping("/courses/create")
    public ResponseEntity<String> createCourse(@RequestBody CreateCourse createCourse, Roles roltype) {
        return coursesService.createCourse(createCourse, roltype);
    }

    @PatchMapping("/courses/delete")
    public ResponseEntity<String> deleteCourses(Long id, Roles roltype) {
        return coursesService.deleteCourses(id, roltype);
    }

    @PatchMapping("/courses/update")
    public ResponseEntity<String> updateCourse(@RequestBody CreateCourse createCourse, Roles roltype, Long id) {
        return coursesService.updateCourse(createCourse, roltype, id);
    }
}

