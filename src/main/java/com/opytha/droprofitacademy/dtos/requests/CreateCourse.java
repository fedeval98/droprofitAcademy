package com.opytha.droprofitacademy.dtos.requests;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;

public class CreateCourse {

    private String name;
    private boolean active = true;


    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
}
