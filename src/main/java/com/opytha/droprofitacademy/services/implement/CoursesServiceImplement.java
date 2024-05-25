package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.repositories.CoursesRepository;
import com.opytha.droprofitacademy.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesServiceImplement implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public Courses findById(Long id) {
        return coursesRepository.findById(id).orElse(null);
    }
}
