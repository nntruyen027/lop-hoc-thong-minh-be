package com.vinhthanh2.lophocdientu.config;


import com.vinhthanh2.lophocdientu.entity.User;
import com.vinhthanh2.lophocdientu.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Optional<User> existingAdmin = userRepository.findByUsername("admin");
            if (existingAdmin.isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin")); // mã hóa password
                admin.setFullName("Administrator");
                admin.setRole("ROLE_ADMIN");
                admin.setBirthday(new Date());
                userRepository.save(admin);
                System.out.println("Created default admin user: admin / admin");
            }
        };
    }
}

