package com.opytha.droprofitacademy.dtos;

import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.models.Videos;

import java.time.LocalDate;

public class VideosDTO {

    private Long id;
    private String videoName, url;
    private LocalDate creationDate;
    private Courses courses;

    public VideosDTO(Videos videos) {
        id = videos.getId();
        videoName = videos.getVideoName();
        url = videos.getUrl();
        creationDate = videos.getCreationDate();
        courses = videos.getCourses();
    }

    public Long getId() {
        return id;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Courses getCourses() {
        return courses;
    }
}
