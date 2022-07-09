package com.entrevueSpringBoot.api.controllerTests;

import com.entrevueSpringBoot.api.controller.FilmController;
import com.entrevueSpringBoot.api.model.Actor;
import com.entrevueSpringBoot.api.model.Film;
import com.entrevueSpringBoot.api.service.FilmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ControllerUseCaseTests {

    private Film film;

    @MockBean
    private FilmService filmService;

    @Autowired
    protected MockMvc mvc;

    @BeforeEach
    public void setup(){
        Actor actorTest1 = new Actor(1L,"lastNameTest1", "firstNameTest1");
        Actor actorTest2 = new Actor(2L,"lastNameTest2", "firstNameTest2");
        List<Actor> actorsListTest = new ArrayList<Actor>(List.of(actorTest1, actorTest2));
        film = new Film(1L, "filmTitleTest", "descriptionTitleTest", actorsListTest);
    }

    @Test
    public void canGetFilmByIdWhenExists() throws Exception {
        // given
        given(filmService.getFilmById(1L))
                .willReturn(film);

        // when
        String uri = "/api/films/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn();

        int status = mvcResult.getResponse().getStatus();

        // then
        assertEquals(200, status);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(mapToJson(film));
    }

    @Test
    public void canRegisterNewFilmTest() throws Exception {
        //when
        String uri = "/api/films";
        String inputJson = mapToJson(film);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        // then
        assertEquals(201, status);
    }



    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
