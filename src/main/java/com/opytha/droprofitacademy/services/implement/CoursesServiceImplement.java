package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.dtos.requests.CreateCourse;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.repositories.ClientsRepository;
import com.opytha.droprofitacademy.repositories.CoursesRepository;
import com.opytha.droprofitacademy.services.ClientService;
import com.opytha.droprofitacademy.services.CoursesService;
import com.opytha.droprofitacademy.services.VideosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoursesServiceImplement implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VideosService videosService;

    @Override
    public Courses findById(Long id) {
        return coursesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public void deleteCourse(Long id){coursesRepository.deleteById(id);}

    @Override
    public void saveCourse(Courses courses) {
        coursesRepository.save(courses);
    }

    @Override
    public Set<CoursesDTO> getAllCoursesDTO() {
        return getAllCourses().stream().map(CoursesDTO::new).collect(Collectors.toSet());
    }

    @Override
    public ResponseEntity<String> createCourse(CreateCourse createCourse, String email) {

        Client client = clientService.findByEmail(email);

        if(client.getRol().equals(Roles.USER)){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        if(createCourse.getName().isBlank()){
            return new ResponseEntity<>("Name can't be blank", HttpStatus.FORBIDDEN);
        }

        Courses course = new Courses(createCourse.getName());

        saveCourse(course);

        return new ResponseEntity<>("Course created successfully", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteCourses(Long id, String email) {

        Client client = clientService.findByEmail(email);

        if(client.getRol().equals(Roles.USER)){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        Courses existingCourse = findById(id);

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
    @Transactional
    public ResponseEntity<String> updateCourse(CreateCourse createCourse, String email, Long id) {

        Client client = clientService.findByEmail(email);

        if(client.getRol().equals(Roles.USER)){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        Courses existingCourse = findById(id);
        if (existingCourse == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        existingCourse.setName(createCourse.getName());

        existingCourse.setActive(createCourse.isActive());

        coursesRepository.save(existingCourse);

        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addVideoToCourse (Long videoId, Long CourseId, String email){
        Client auth = clientService.findByEmail(email);

        if(auth.getRol().equals(Roles.USER)){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        Videos video = videosService.findById(videoId);

        if(video == null){
            return new ResponseEntity<>("Video not found", HttpStatus.NOT_FOUND);
        }

        Courses course = findById(CourseId);

        if(course == null){
            return new ResponseEntity<>("Course not found",HttpStatus.NOT_FOUND);
        }

        course.addVideos(video);

        return new ResponseEntity<>("Video added successfully to course", HttpStatus.OK);
    }

}
