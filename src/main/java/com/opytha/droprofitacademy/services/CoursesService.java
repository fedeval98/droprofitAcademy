package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.Courses;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CoursesService {

    Courses findById (Long id);
    Set<Courses> getAllCourses ();
    Set<CoursesDTO> getAllCoursesDTO ();
    Set<CoursesDTO> getAllCoursesDisabled ();
    ResponseEntity<String> createCourse (CreateCourse createCourse);
    ResponseEntity<String> deleteCourse (Long courseId);
    void saveCourse (Courses courses);
}
