package com.example.cleanblogservice.fortest.after.infra;

import com.example.cleanblogservice.fortest.PostVO;
import com.example.cleanblogservice.fortest.after.PostVORedisService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class LettuceRedisService implements PostVORedisService {
    
    private final RedisTemplate<String, PostVO> redisTemplate;
    
    public LettuceRedisService(RedisTemplate<String, PostVO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    
    @Override
    public Optional<PostVO> get(String key) {
        var savedVO = redisTemplate.opsForValue().get(generateKey(key));
        return Optional.ofNullable(savedVO);
    }
    
    @Override
    public void set(String key, Object value) {
    
    }
    
    private String generateKey(String title) {
        return LocalDateTime.now().toString() + "post:" + title;
    }
}
