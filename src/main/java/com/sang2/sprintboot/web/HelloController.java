package com.sang2.sprintboot.web;

import com.sang2.sprintboot.web.dto.HelloResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/hello/dto")
    public ResponseEntity<HelloResponseDto> helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return ResponseEntity.ok(new HelloResponseDto(name, amount));
    }
}
