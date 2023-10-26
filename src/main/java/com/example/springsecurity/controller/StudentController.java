package com.example.springsecurity.controller;

import com.example.springsecurity.dao.StudentRepository;
import com.example.springsecurity.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Student area!!!";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Student";
    }

    @PostMapping("/add/username/{username}/password/{password}")
    public Student addStudent(@PathVariable("username") String username,
                      @PathVariable("password") String password){


        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setRole("ROLE_STUDENT,ROLE_RANDOM");

        Student savedStudent = studentRepository.save(student);
        return savedStudent;

    }
}
