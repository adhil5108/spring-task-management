package com.example.todowithsecurity.controller;

import com.example.todowithsecurity.model.Roles;
import com.example.todowithsecurity.model.Users;
import com.example.todowithsecurity.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Userrepo userrepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public List<Users> get() {
        return userrepo.findAll();
    }

    @GetMapping("{id}")
    public Optional<Users> getbyid(@PathVariable Long id) {
        return userrepo.findById(id);
    }

    @PostMapping("/register")
    public Users post(@RequestBody Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        if (users.getRoles() == null) {
            users.setRoles(Roles.ROLE_USER);
        }
        return userrepo.save(users);
    }

    @GetMapping("/whoami")
    public String whoami(Authentication authentication) {
        return "Username: " + authentication.getName() + " | Authorities: " + authentication.getAuthorities();
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteuser(@PathVariable Long id) {
        userrepo.deleteById(id);
        return "user with " + id + " deleted succesfully";
    }
}
