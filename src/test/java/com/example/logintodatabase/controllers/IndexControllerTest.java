package com.example.logintodatabase.controllers;

import com.example.logintodatabase.models.services.UserSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {

    @InjectMocks
    IndexController indexController;

    @Mock
    UserSession userSession;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("If user is not login, redirect him to login page")
    @ParameterizedTest
    @ValueSource(strings = {"", "index", "image", "asdasdasdsad", "asdsa?asd=asd&ad=asd"})
    public void shouldRedirectToLoginPageIfUserNotLogin(String url) throws Exception {
        //given
        boolean isUserLogin = false;

        //when
        Mockito.when(userSession.isLogin()).thenReturn(isUserLogin);

        //then
        //Assert.assertTrue(indexController.getUserSession().isLogin());
        mockMvc.perform(get("/" + url))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login-form"));
    }
}