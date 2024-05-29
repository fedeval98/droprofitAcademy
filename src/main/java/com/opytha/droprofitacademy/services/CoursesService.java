package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.models.enums.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public interface CoursesService {

    Courses findById (Long id);

    void deleteCourse(Long id);

    void saveCourse(Courses courses);

    Set<Courses> getAllCourses ();

    Set<CoursesDTO> getAllCoursesDTO ();

    Set<CoursesDTO> getAllCoursesDisabled ();

    ResponseEntity<String> createCourse(CreateCourse createCourse, Roles roltype);

    ResponseEntity<String> deleteCourses(Long id, Roles roltype);

    ResponseEntity<String> updateCourse(CreateCourse createCourse, Roles roltype, Long id);
}
