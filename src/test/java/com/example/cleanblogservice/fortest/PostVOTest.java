package com.example.cleanblogservice.fortest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("PostVO 테스트")
class PostVOTest {
    
    
    @Nested
    @DisplayName("PostVO 생성자는")
    class Description_of_constructor {
        
        @Nested
        @DisplayName("title이 10글자 미만이면 IllegalArgumentException 발생")
        class Context_of_title_length_is_under_10 {
           
            
            @Test
            @DisplayName("IllegalArgumentException 발생")
            void title_length_less_than_10() {
                assertThrows(IllegalArgumentException.class, () -> new PostVO("123456789", "content"));
            }
            
            @Test
            @DisplayName("title이 10글자 이상이면 정상 생성")
            void title_length_greater_than_10() {
                assertDoesNotThrow(() -> new PostVO("1234567890", "content"));
            }
        }
    }
}