package com.example.todowithsecurity.repo;

import com.example.todowithsecurity.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Long> {
    List<Todo> findByUser_Id(Long userId);
}
