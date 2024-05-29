package com.opytha.droprofitacademy.dtos;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;

public class ClientDTO {

    private Long id;

    private String firstName, lastName, email;

    private Roles rol;


    public ClientDTO(Client client) {
        id = client.getId();
        firstName = client.getFirstName();
        lastName = client.getLastName();
        email = client.getEmail();
        rol = client.getRol();
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

    public Roles getRol() {
        return rol;
    }
}
