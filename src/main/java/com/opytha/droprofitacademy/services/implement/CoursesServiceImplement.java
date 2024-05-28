package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.repositories.ClientsRepository;
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

    @Autowired
    private ClientsRepository clientsRepository;

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
    public ResponseEntity<String> createCourse(CreateCourse createCourse) {

        if(createCourse.getName().isBlank()){
            return new ResponseEntity<>("Name can't be blank", HttpStatus.FORBIDDEN);
        }
        if(createCourse.getClient() == null){
            return new ResponseEntity<>("Client information is required", HttpStatus.FORBIDDEN);
        }
        Client client = clientsRepository.findById(createCourse.getClient().getId()).orElse(null);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        Courses course = new Courses();
        course.setName(createCourse.getName());
        course.setClient(client);
        course.setActive(true);

        coursesRepository.save(course);

        return new ResponseEntity<>("Course created successfully", HttpStatus.CREATED);
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
    public ResponseEntity<String> updateCourse(CoursesDTO coursesDTO) {

        Courses existingCourse = coursesRepository.findById(coursesDTO.getId()).orElse(null);
        if (existingCourse == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
        existingCourse.setName(coursesDTO.getName());
        existingCourse.setActive(coursesDTO.isActive());
        coursesRepository.save(existingCourse);
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }


    @Override
    public void saveCourse(Courses courses) {
        coursesRepository.save(courses);
    }
}
