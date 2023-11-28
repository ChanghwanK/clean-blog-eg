package com.example.cleanblogservice.fortest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cleanblogservice.before.dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@ExtendWith(MockitoExtension.class)
@DisplayName("PostServiceHard 테스트")
class PostServiceHardTest {
    
    /**
     * Test를 위한 RedisTemplate 목킹 (비용이 비쌈)
     */
    @Mock
    private RedisTemplate<String, PostVO> redisTemplate;
    
    /**
     * Test를 위한 RedisTemplate의 Operation 목킹
     */
    @Mock
    private ValueOperations valueOperations;
    
    
    private PostServiceHard sut;
    
    @BeforeEach
    void setUp() {
        sut = new PostServiceHard(redisTemplate);
    }
    
    @Nested
    @DisplayName("updateContent 메서드는")
    class Describe_of_setPost {
        
        private final String title = "title-1234567890";
        
        @Nested
        @DisplayName("10글자 이상이면서 기존 값이 존재한다면")
        class Context_of_title_length_greater_than_10 {
            
            @Test
            @DisplayName("정상적으로 update 된다.")
            void it_is_stored() {
                var FIXTURE = new PostDto.UpdateRequest(title, "content");
                when(redisTemplate.opsForValue()).thenReturn(valueOperations);
                when(valueOperations.get(title)).thenReturn(FIXTURE);
                
                sut.updateContent(new PostDto.UpdateRequest(title, "content-123"));
                
                verify(valueOperations, times(1)).set("broker:1", "fromFleet");
            }
        }
    }
    
}