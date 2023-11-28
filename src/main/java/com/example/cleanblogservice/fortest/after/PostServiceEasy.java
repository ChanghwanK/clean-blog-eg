package com.example.cleanblogservice.fortest.after;

import com.example.cleanblogservice.before.dto.PostDto;
import com.example.cleanblogservice.fortest.PostVO;
import org.springframework.stereotype.Service;

@Service
public class PostServiceEasy {
    
    private final PostVORedisService redisService;
    
    public PostServiceEasy(PostVORedisService redisService) {
        this.redisService = redisService;
    }
    
    public void setPost(PostDto.RegisterRequest postRegisterRequest) {
        var postVO = postRegisterRequest.toValue();
        redisService.set(postVO.title(), postVO);
    }
    
    public void updateContent(PostDto.UpdateRequest postUpdateRequest) {
        var value = redisService.get(postUpdateRequest.title())
            .orElseThrow(() -> new IllegalArgumentException("post not found"));
        
        PostVO postVO = new PostVO(value.title(), postUpdateRequest.content());
        redisService.set(postVO.title(), postVO);
    }
}
