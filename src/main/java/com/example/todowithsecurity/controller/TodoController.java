package com.example.todowithsecurity.controller;

import com.example.todowithsecurity.model.Todo;
import com.example.todowithsecurity.model.Users;
import com.example.todowithsecurity.repo.TodoRepo;
import com.example.todowithsecurity.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private Userrepo userRepo;

    @GetMapping
    public List<Todo> getTodos(Authentication authentication) {
        String username = authentication.getName(); // logged-in user's username
        Users user = userRepo.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow();

        return todoRepo.findAll().stream()
                .filter(todo -> todo.getUser().getId().equals(user.getId()))
                .toList();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo, Authentication authentication) {
        String username = authentication.getName();
        Users user = userRepo.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow();

        todo.setUser(user);
        return todoRepo.save(todo);
    }
}
