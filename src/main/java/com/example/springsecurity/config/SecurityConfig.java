package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration

public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getUserDetailService(){

//        UserDetails student = User.withUsername("Virat")
//                .password(getPasswordEncoder().encode("virat123"))
//                .roles("STUDENT")
//                .build();
//
//        UserDetails student1 = User.withUsername("Bomra")
//                .password(getPasswordEncoder().encode("bomra123"))
//                .roles("STUDENT")
//                .build();
//
//        UserDetails admin = User.withUsername("Rohit")
//                .password(getPasswordEncoder().encode("rohit123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManag er(student,admin, student1);

        return new CustomUserDetailsService();
    }


    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity. csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/pubic/**")
                .permitAll()
                .requestMatchers("/student/add/**")
                .permitAll()
                .requestMatchers("/student/welcome")
                .hasAnyRole("STUDENT","ADMIN")
                .requestMatchers("/admin/add/**")
                .permitAll()
                .requestMatchers("/admin/welcome ")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
}
