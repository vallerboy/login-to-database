package com.example.logintodatabase.controllers;

import com.example.logintodatabase.models.User;
import com.example.logintodatabase.models.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddUserControllerTest {

    @InjectMocks
    AddUserController userController;

    @Mock
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddUser() throws Exception {
        //given
        User user = new User();
        user.setName("hello123");
        user.setPassword("hello123");
        user.setId(null);
        user.setTime(null);


        //when
        Mockito.when(userService.addUser(user)).thenReturn(true);

        //then
        mockMvc.perform(post("/add-user")
                        .param("name", "hello123")
                        .param("password", "hello123")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Thank you!!!"));

    }

}