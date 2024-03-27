package com.springboot.restful.api.config;

import com.springboot.restful.api.domain.Post;
import com.springboot.restful.api.domain.User;
import com.springboot.restful.api.dto.AuthorDTO;
import com.springboot.restful.api.repository.PostRepository;
import com.springboot.restful.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User firstUser = new User(null, "Maria Ferreira", "maria.ferreira@gmail.com");
        User secondUser = new User(null, "Paulo Neto", "paulo.neto@gmail.com");
        User thirdUser = new User(null, "Bianca Silva", "bianca_silva@hotmail.com");
        userRepository.saveAll(Arrays.asList(firstUser, secondUser, thirdUser));

        Post firstPost = new Post(null, sdf.parse("21/03/2024"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(firstUser));
        Post secondPost = new Post(null, sdf.parse("24/03/2024"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(firstUser));
        postRepository.saveAll(Arrays.asList(firstPost, secondPost));

        firstUser.getPosts().addAll(Arrays.asList(firstPost, secondPost));
        userRepository.save(firstUser);

    }
}
