package com.sang2.sprintboot.web;

import com.sang2.sprintboot.service.PostsService;
import com.sang2.sprintboot.web.dto.PostsRequestDto;
import com.sang2.sprintboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsRequestDto postsRequestDto) {
        return postsService.save(postsRequestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsRequestDto postsRequestDto) {
        return postsService.update(id, postsRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
