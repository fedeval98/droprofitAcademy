package com.opytha.droprofitacademy.repositories;

import com.opytha.droprofitacademy.models.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VideosRepository extends JpaRepository<Videos,Long> {
}
