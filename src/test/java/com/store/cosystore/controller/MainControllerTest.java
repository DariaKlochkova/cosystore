package com.store.cosystore.controller;

import com.store.cosystore.repos.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepo userRepo;

    @Test
    void main() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void register() throws Exception {
        mockMvc.perform(post("/registration").with(csrf())
                .param("firstname", "Пётр")
                .param("surname", "Петров")
                .param("username", "petya")
                .param("password", "111"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
        Assert.assertNotNull(userRepo.findByUsername("petya"));

        mockMvc.perform(post("/registration").with(csrf())
                .param("firstname", "Пётр")
                .param("surname", "Иванов")
                .param("username", "petya")
                .param("password", "222"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Такой логин уже существует")));
    }
}