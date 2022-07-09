package com.entrevueSpringBoot.api.serviceTests;

import com.entrevueSpringBoot.api.model.Actor;
import com.entrevueSpringBoot.api.model.Film;
import com.entrevueSpringBoot.api.repository.FilmRepository;
import com.entrevueSpringBoot.api.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilmServiceUseCaseTests {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    private Film film;

    @BeforeEach
    public void setup(){
        Actor actorTest1 = new Actor(1L,"lastNameTest1", "firstNameTest1");
        Actor actorTest2 = new Actor(2L,"lastNameTest2", "firstNameTest2");
        List<Actor> actorsListTest = new ArrayList<Actor>(List.of(actorTest1, actorTest2));
        film = new Film(1L, "filmTitleTest", "descriptionTitleTest", actorsListTest);
    }

    @DisplayName("JUnit test for addNewFilm method")
    @Test
    void CanAddNewFilm() {
        // given
        given(filmRepository.save(film)).willReturn(film);

        // when
        Film savedFilm = filmService.addNewFilm(film);

        // then
        assertThat(savedFilm).isNotNull();
    }

    @DisplayName("JUnit test for getFilmById method")
    @Test
    public void CanGetFilmById(){
        // given
        given(filmRepository.findById(1L)).willReturn(Optional.of(film));

        // when
        Film savedFilm = filmService.getFilmById(film.getId());

        // then
        assertThat(savedFilm).isNotNull();
    }
}
