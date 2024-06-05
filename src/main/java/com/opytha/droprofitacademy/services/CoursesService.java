package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.models.enums.Roles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

public interface CoursesService {

    Courses findById (Long id);

    void deleteCourse(Long id);

    void saveCourse(Courses courses);

    CoursesDTO getCourse(Long id);

    List<Courses> getAllCourses ();

    Set<CoursesDTO> getAllCoursesDTO ();

    ResponseEntity<String> createCourse(CreateCourse createCourse, String email);

    ResponseEntity<String> deleteCourses(Long id, String email);

    ResponseEntity<String> updateCourse(CreateCourse createCourse, String email, Long id);

    ResponseEntity<String> addVideoToCourse(Long videoId, Long CourseId, String email);

}
