package com.sang2.sprintboot.web;

import com.sang2.sprintboot.domain.posts.Posts;
import com.sang2.sprintboot.domain.posts.PostsRepository;
import com.sang2.sprintboot.web.dto.PostsRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    void save() {
        //given
        String title = "title";
        String content = "content";
        PostsRequestDto postsRequestDto = PostsRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, postsRequestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    void update() {
        //given
        String title = "title";
        String content = "content";
        PostsRequestDto postsRequestDto = PostsRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        Posts posts = postsRepository.save(postsRequestDto.toEntity());
        Long savedId = posts.getId();
        String updatedTitle = "title2";
        String updatedContent = "content2";

        String url = "http://localhost:" + port + "/api/v1/posts/" + savedId;
        PostsRequestDto updateRequestDto = PostsRequestDto.builder().title(updatedTitle).content(updatedContent).build();

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(updateRequestDto), Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updatedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(updatedContent);
    }
}