package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.services.ClientService;
import com.opytha.droprofitacademy.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @Autowired
    ClientService clientService;

    @GetMapping("/courses")
    public Set<CoursesDTO> getAllCoursesDTO() {
        return coursesService.getAllCoursesDTO();
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CoursesDTO> getCourse(@PathVariable Long id, Authentication authentication){

        Client client = clientService.getAuthClient(authentication.getName());

        if(client == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CoursesDTO coursesDTO = coursesService.getCourse(id);
        if (coursesDTO != null) {
            return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/courses/create")
    public ResponseEntity<String> createCourse(@RequestBody CreateCourse createCourse, Authentication authentication) {
        return coursesService.createCourse(createCourse, authentication.getName());
    }

    @PatchMapping("/courses/delete")
    public ResponseEntity<String> deleteCourses(@RequestParam Long id, Authentication authentication) {
        return coursesService.deleteCourses(id, authentication.getName());
    }

    @PatchMapping("/courses/update")
    public ResponseEntity<String> updateCourse(@RequestBody CreateCourse createCourse, Authentication authentication, @RequestParam Long id) {
        return coursesService.updateCourse(createCourse, authentication.getName(), id);
    }

    @PatchMapping("/courses/addVideos")
    public ResponseEntity<String> addVideoToCourse(@RequestParam Long videoId, @RequestParam Long CourseId, Authentication authentication){
        return coursesService.addVideoToCourse(videoId, CourseId, authentication.getName());
    }
}

