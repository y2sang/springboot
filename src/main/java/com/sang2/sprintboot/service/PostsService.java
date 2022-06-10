package com.sang2.sprintboot.service;

import com.sang2.sprintboot.domain.posts.Posts;
import com.sang2.sprintboot.domain.posts.PostsRepository;
import com.sang2.sprintboot.web.dto.PostsRequestDto;
import com.sang2.sprintboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsRequestDto postsRequestDto) {
        return postsRepository.save(postsRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsRequestDto postsRequestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post => " + id));
        posts.update(postsRequestDto);
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts postsEntity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No post => " + id));
        return new PostsResponseDto(postsEntity);
    }
}
