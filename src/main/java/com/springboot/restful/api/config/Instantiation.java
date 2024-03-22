package com.springboot.restful.api.config;

import com.springboot.restful.api.domain.User;
import com.springboot.restful.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User first = new User(null, "Maria Ferreira", "maria.ferreira@gmail.com");
        User second = new User(null, "Paulo Neto", "paulo.neto@gmail.com");
        User third = new User(null, "Bianca Silva", "bianca_silva@hotmail.com");

        userRepository.saveAll(Arrays.asList(first, second, third));

    }
}
