package com.example.cleanblogservice.before.repository;


import com.example.cleanblogservice.before.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {

    private final ConcurrentHashMap<Integer, PostEntity> store = new ConcurrentHashMap<>();

    public Optional<PostEntity> findByTitle(String title) {

        return Optional.ofNullable(store.entrySet().stream()
                .filter(entry -> entry.getValue().getTitle().equals(title))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null));

    }
}
