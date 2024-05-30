package com.opytha.droprofitacademy.dtos;

import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.models.Videos;

import java.time.LocalDate;
import java.util.Set;

public class VideosDTO {

    private Long id;
    private String videoName, url;
    private LocalDate creationDate;

    public VideosDTO(Videos videos) {
        id = videos.getId();
        videoName = videos.getVideoName();
        url = videos.getUrl();
        creationDate = videos.getCreationDate();
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

}
