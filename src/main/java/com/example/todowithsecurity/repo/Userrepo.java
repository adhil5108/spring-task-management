package com.example.todowithsecurity.repo;

import com.example.todowithsecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Userrepo extends JpaRepository<Users,Long> {
   Optional<Users> findByUsername(String username);
}
