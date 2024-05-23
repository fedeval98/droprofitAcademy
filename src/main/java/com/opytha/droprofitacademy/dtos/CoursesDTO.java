package com.opytha.droprofitacademy.dtos;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;
import jakarta.persistence.ManyToOne;

public class CoursesDTO {

    private Long id;
    private String name;
    private Client client;

    public CoursesDTO(Courses courses) {
        id = courses.getId();
        name = courses.getName();
        client = courses.getClient();
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
}
