package com.example.springsecurity.config;

import com.example.springsecurity.dao.StudentRepository;
import com.example.springsecurity.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);

        if(student == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        return new UserDetailsCreator(student);
    }
}
