package com.action.reader.readerwriterreplica.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.action.reader.readerwriterreplica.entity.User;
import com.action.reader.readerwriterreplica.repository.UserRepository;

import java.util.Optional;

@Component
public class TestDataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            User user = new User();
            user.setUsername("test_user");
            user.setEmail("test_user@example.com");

            userRepository.save(user);
            System.out.println("User saved!");
            for (int i=0;i<100;i++)
            {
                Optional<User> list= userRepository.findById(1L);
            }

        };
    }
}