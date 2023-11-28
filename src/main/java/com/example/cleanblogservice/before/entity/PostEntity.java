package com.example.cleanblogservice.before.entity;

public class PostEntity {
    private final String title;
    private final String content;

    public PostEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }

    public String getContent() { return content;}

    public void checkTitleDuplicated(final String title) {
        if (this.title.equals(title))
            throw new IllegalArgumentException("중복된 Title 입니다.");
    }
}
