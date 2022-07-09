package com.entrevueSpringBoot.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.entrevueSpringBoot.api.controller.FilmController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTests {

    @Autowired
    private FilmController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
