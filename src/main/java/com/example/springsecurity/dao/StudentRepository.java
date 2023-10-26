package com.example.springsecurity.dao;

import com.example.springsecurity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUsername(String username);
}
