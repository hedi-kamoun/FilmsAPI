package com.entrevueSpringBoot.api.controller;

import com.entrevueSpringBoot.api.model.Film;
import com.entrevueSpringBoot.api.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService)
    {
        this.filmService = filmService;
    }

    @GetMapping(path = "api/films/{id}")
    public Film getFilmById(@PathVariable Long id){
        return filmService.getFilmById(id);
    }

    @PostMapping(path = "api/films")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Objet Film crée")
    public void registerNewFilm(@RequestBody Film film){
        filmService.addNewFilm(film);
    }
}
