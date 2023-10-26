package com.example.springsecurity.controller;

import com.example.springsecurity.dao.StudentRepository;
import com.example.springsecurity.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Admin area!!!";
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/add/username/{username}/password/{password}")
    public Student addStudent(@PathVariable("username") String username,
                              @PathVariable("password") String password){


        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setRole("ROLE_ADMIN");

        Student savedStudent = studentRepository.save(student);
        return savedStudent;

    }
}
