package com.example.cleanblogservice.before.controller;

import com.example.cleanblogservice.before.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/v1/post")
    public void registerPost() {

    }
}
