package com.opytha.droprofitacademy.models;

import com.opytha.droprofitacademy.models.enums.Roles;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName, lastName, email, password;

    private Roles rol = Roles.USER;

    @OneToMany (mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Courses> courses = new HashSet<>();

    public Client() {

    }

    public Client(String firstName, String lastName, String email, String password, Roles rol) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public void addCourses( Courses courses){
        courses.setClient(this);
        this.courses.add(courses);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

}