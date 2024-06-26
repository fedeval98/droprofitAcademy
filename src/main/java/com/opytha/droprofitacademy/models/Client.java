package com.opytha.droprofitacademy.models;

import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.models.enums.Status;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName, lastName, email, password;

    private int userID;

    @Enumerated(EnumType.STRING)
    private Roles rol = Roles.USER;

    @OneToMany (mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Courses> courses = new HashSet<>();

    private boolean active = true;

    public Client() {

    }

    public Client(String firstName, String lastName, String email, String password, int uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userID = uid;
        this.password = password;;
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

    public Set<Courses> getCourses() {
        return courses;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}