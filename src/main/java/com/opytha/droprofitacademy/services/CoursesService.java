package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.CoursesDTO;
import com.opytha.droprofitacademy.models.Courses;

import java.util.Set;

public interface CoursesService {

    Courses findById (Long id);
    Set<Courses> getAllCourses ();
    Set<CoursesDTO> getAllCoursesDTO ();
    Set<CoursesDTO> getAllCoursesDisabled ();
    void deleteCourse (Courses courses);
    void saveCourse (Courses courses);
}
