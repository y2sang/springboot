package com.sang2.sprintboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;


    @Test
    void save_get_posts() {
        //given
        String title = "타이틀";
        String content = "Content 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("test@test.com").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @AfterEach
    public void cleanUpEach() {
        postsRepository.deleteAll();
    }
}