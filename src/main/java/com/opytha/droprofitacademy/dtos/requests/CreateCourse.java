package com.opytha.droprofitacademy.dtos.requests;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;

public class CreateCourse {

    private String name, img;
    private boolean active = true;


    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public boolean isActive() {
        return active;
    }
}
