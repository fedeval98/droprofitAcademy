package com.opytha.droprofitacademy.dtos;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;
import jakarta.persistence.ManyToOne;

import java.util.Set;
import java.util.stream.Collectors;

public class CoursesDTO {

    private Long id;
    private String name;
    private Set<VideosDTO> videos;
    private boolean active = true;

    public CoursesDTO(Courses courses) {
        id = courses.getId();
        name = courses.getName();
        videos = courses.getVideos().stream().map(VideosDTO::new).collect(Collectors.toSet());
        active = courses.isActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<VideosDTO> getVideos() {
        return videos;
    }

    public boolean isActive() {
        return active;
    }
}
