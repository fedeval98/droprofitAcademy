package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
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

    @PatchMapping("/delete")
    public ResponseEntity<String> deleteCourses(
            @RequestParam Long courseId,
            Authentication authentication) {

        Courses course = coursesService.findById(courseId);
        if (course == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        if (course.isActive()) {
            coursesService.deleteCourse(course);
            return new ResponseEntity<>("Course deactivated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Course already deactivated", HttpStatus.BAD_REQUEST);
        }

    }
}

