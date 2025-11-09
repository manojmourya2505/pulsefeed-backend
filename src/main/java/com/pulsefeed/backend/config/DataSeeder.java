package com.pulsefeed.backend.config;

import com.pulsefeed.backend.model.Post;
import com.pulsefeed.backend.model.User;
import com.pulsefeed.backend.repository.PostRepository;
import com.pulsefeed.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner sendDatabase(UserRepository userRepository, PostRepository postRepository, BCryptPasswordEncoder bCryptPasswordEncoder){

        return args -> {
            if (userRepository.count() == 0){
                User user = new User();
                user.setEmail("timcook@gmail.com");
                user.setUserName("Tim Cook");
                user.setPassword("123456789");
                userRepository.save(user);

                Post postOne = new Post("https://images.pexels.com/photos/34106378/pexels-photo-34106378.jpeg","Morning Vide",user);
                Post postTwo = new Post("https://images.pexels.com/photos/34106378/pexels-photo-34106378.jpeg","Evening Vide",user);
                postRepository.saveAll(List.of(postOne,postTwo));

                System.out.println("✅ Sample user + posts seeded successfully.");
            }else{
                System.out.println("Database already contains data, skipping seeding.");
            }
        };
    }
}
