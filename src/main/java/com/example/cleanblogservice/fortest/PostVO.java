package com.example.cleanblogservice.fortest;

/**
 * 캡슐화가 적용된 케이스
 */
public record PostVO(String title, String content) {
    public PostVO {
        if (title.length() < 10) {
            throw new IllegalArgumentException("title length must be greater than 10");
        }
    }
}