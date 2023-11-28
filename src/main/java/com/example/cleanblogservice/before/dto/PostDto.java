package com.example.cleanblogservice.before.dto;

import com.example.cleanblogservice.before.entity.PostEntity;
import com.example.cleanblogservice.fortest.PostVO;

public class PostDto {
    
    public record RegisterRequest(String title, String content) {
        
        public PostEntity toEntity() {
            return new PostEntity(title, content);
        }
        
        public PostVO toValue() {
            return new PostVO(title, content);
        }
    }
    
    public record UpdateRequest(String title, String content) { }
}
