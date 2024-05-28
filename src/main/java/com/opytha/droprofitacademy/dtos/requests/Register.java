package com.opytha.droprofitacademy.dtos.requests;

import com.opytha.droprofitacademy.models.enums.Roles;

public class Register {

    private String firstName, lastName, email, password;

    private Roles type;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Roles getType() {
        return type;
    }
}
