package com.opytha.droprofitacademy.dtos.requests;

import com.opytha.droprofitacademy.models.Courses;

public class newVideo {

    private String videoName, url;

    private Courses courses;

    public String getVideoName() {
        return videoName;
    }

    public String getUrl() {
        return url;
    }

    public Courses getCourses() {
        return courses;
    }
}
