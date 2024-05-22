package com.opytha.droprofitacademy.repositories;

import com.opytha.droprofitacademy.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CoursesRepository extends JpaRepository<Courses,Long> {
}
