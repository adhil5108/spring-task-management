package com.example.todowithsecurity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;
    private String task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Todo(Long id, String task, Users user) {
        this.id = id;
        this.task = task;
        this.user = user;
    }

    public Todo() {
    }
}
