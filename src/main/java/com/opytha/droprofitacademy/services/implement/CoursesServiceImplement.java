package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.repositories.CoursesRepository;
import com.opytha.droprofitacademy.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoursesServiceImplement implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public Courses findById(Long id) {
        return coursesRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Courses> getAllCourses() {
        List<Courses> coursesList = coursesRepository.findAll();
        return coursesList.stream().collect(Collectors.toSet());
    }

    @Override
    public Set<CoursesDTO> getAllCoursesDTO() {
        return getAllCourses().stream().map(courses -> new CoursesDTO(courses)).collect(Collectors.toSet());
    }

    @Override
    public Set<CoursesDTO> getAllCoursesDisabled() {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteCourse(Long courseId) {

        Courses existingCourse = coursesRepository.findById(courseId).orElse(null);

        if (existingCourse == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        if (existingCourse.isActive()) {
            return new ResponseEntity<>("Course already deactivated", HttpStatus.BAD_REQUEST);
        }

        existingCourse.setActive(false);
        coursesRepository.save(existingCourse);

        return new ResponseEntity<>("Course deactivated", HttpStatus.OK);
    }


    @Override
    public void saveCourse(Courses courses) {
        coursesRepository.save(courses);
    }
}
