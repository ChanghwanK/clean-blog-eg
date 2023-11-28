package com.example.cleanblogservice.fortest.after;

import com.example.cleanblogservice.fortest.PostVO;
import java.util.Optional;

public interface PostVORedisService {
    
    Optional<PostVO> get(final String key);

    void set(final String key, final Object value);
}
