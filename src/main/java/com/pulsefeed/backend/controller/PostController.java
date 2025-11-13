package com.pulsefeed.backend.controller;

import com.pulsefeed.backend.model.Post;
import com.pulsefeed.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String imageUrl = request.get("imageUrl");
        String caption = request.get("caption");

        Post post = postService.createPost(imageUrl, caption, email);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<Post>> getFeed() {
        List<Post> posts = postService.fetchAllPosts();
        return ResponseEntity.ok(posts);
    }
}
