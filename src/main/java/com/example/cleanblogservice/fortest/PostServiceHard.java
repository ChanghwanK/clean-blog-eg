package com.example.cleanblogservice.fortest;

import com.example.cleanblogservice.before.dto.PostDto;
import java.time.LocalDateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostServiceHard {
    
    private final RedisTemplate<String, PostVO> redisTemplate;
    
    public PostServiceHard(RedisTemplate<String, PostVO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    public void setPost(PostDto.RegisterRequest postRegisterRequest) {
        checkMinLength(postRegisterRequest.content());
        
        var postVO = postRegisterRequest.toValue();
        redisTemplate.opsForValue().set(generateKey(postVO.title()), postVO);
    }
    
    public void updateContent(PostDto.UpdateRequest postUpdateRequest) {
        // post content length check
        checkMinLength(postUpdateRequest.content());
        var key = generateKey(postUpdateRequest.title());
        
        var value = redisTemplate.opsForValue().get(key);
        
        if (value == null) {
            throw new IllegalArgumentException("post not found");
        }
        
        PostVO postVO = new PostVO(value.title(), postUpdateRequest.content());
        redisTemplate.opsForValue().set(key, postVO);
    }
    
    /**
     * no private e.g
     * -> 사실 이것도 그냥 행위 검증으로 테스트 가능함 왜냐 테스트 코드에 사용될 sut가 mock이 아니기 때문에
     */
    private void checkMinLength(String content) {
        if (content.length() < 10) {
            throw new IllegalArgumentException("content length must be greater than 10");
        }
    }
    
    
    // yes private e.g
    private String generateKey(String title) {
        return LocalDateTime.now().toString() + "post:" + title;
    }
}
