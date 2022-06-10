package com.sang2.sprintboot.web.dto;

import com.sang2.sprintboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    @Builder
    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getTitle();
        this.author = posts.getAuthor();
    }

    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
