package com.opytha.droprofitacademy.dtos.requests;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;

public class CreateCourse {

    private String name;
    private Client client;
    private boolean active = true;

    public CreateCourse(Courses courses) {
        name = courses.getName();
        client = courses.getClient();
        active = courses.isActive();
    }

    public String getName() {
        return name;
    }

    public Client getClient() {
        return client;
    }

    public boolean isActive() {
        return active;
    }
}
