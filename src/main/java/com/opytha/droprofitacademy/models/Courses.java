package com.opytha.droprofitacademy.models;

import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;

@Entity
public class Courses {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Client client;

    @OneToMany (mappedBy = "videos", fetch = FetchType.EAGER)
    private Set<Videos> videos = new HashSet<>();

    public Courses() {
    }

    public Courses(String name, Client client) {
        this.name = name;
        this.client = client;
    }

    public void addVideos(Videos videos){
        videos.setCourses(this);
        this.videos.add(videos);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}