package com.pulsefeed.backend.services;

import com.pulsefeed.backend.model.Post;
import com.pulsefeed.backend.model.User;
import com.pulsefeed.backend.repository.PostRepository;
import com.pulsefeed.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(String imageUrl, String caption,String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("User not found with email: "  + email);
        }

        User user = optionalUser.get();
        Post post = new Post(imageUrl,caption,user);
        return postRepository.save(post);
    }

    public List<Post> fetchAllPosts(){
        return postRepository.findAll();
    }


}
