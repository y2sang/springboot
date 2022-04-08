package com.sang2.sprintboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_return() throws Exception {
        String hello = "Hello";
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.TEXT_PLAIN);

        mvc.perform(reqBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_return() throws Exception {
        String name = "hello";
        int amount = 1000;
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/hello/dto")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("name", name)
                .param("amount", String.valueOf(amount));
        mvc.perform(reqBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("amount").value(amount));
    }
}
