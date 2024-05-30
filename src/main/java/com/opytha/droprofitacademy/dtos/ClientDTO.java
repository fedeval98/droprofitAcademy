package com.opytha.droprofitacademy.dtos;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Long id;

    private String firstName, lastName, email;

    private Roles rol;

    private Set<CoursesDTO> courses;


    public ClientDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
        rol = client.getRol();
        courses = client.getCourses().stream().map(CoursesDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<CoursesDTO> getCourses() {
        return courses;
    }

    public Roles getRol() {
        return rol;
    }
}
