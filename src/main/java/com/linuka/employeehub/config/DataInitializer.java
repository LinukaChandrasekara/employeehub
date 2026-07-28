package com.linuka.employeehub.config;

import com.linuka.employeehub.entity.User;
import com.linuka.employeehub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitializer {


    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {


        return args -> {


            if(userRepository.findByUsername("admin").isEmpty()) {


                User admin = new User();

                admin.setUsername("admin");

                admin.setPassword(
                        passwordEncoder.encode("admin")
                );

                admin.setRole("ADMIN");


                userRepository.save(admin);
            }
            if(userRepository.findByUsername("user").isEmpty()) {

                User user = new User();

                user.setUsername("user");

                user.setPassword(
                        passwordEncoder.encode("user")
                );

                user.setRole("USER");

                userRepository.save(user);
            }

        };
    }

}