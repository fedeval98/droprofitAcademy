package com.opytha.droprofitacademy.dtos;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;
import jakarta.persistence.ManyToOne;

import java.util.Set;
import java.util.stream.Collectors;

public class CoursesDTO {

    private Long id;
    private String name;
    private Client client;

    private Set<VideosDTO> videos;

    public CoursesDTO(Courses courses) {
        id = courses.getId();
        name = courses.getName();
        client = courses.getClient();
        videos = courses.getVideos().stream().map(VideosDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Client getClient() {
        return client;
    }

    public Set<VideosDTO> getVideos() {
        return videos;
    }
}
