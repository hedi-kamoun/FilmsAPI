package com.entrevueSpringBoot.api.service;

import com.entrevueSpringBoot.api.model.Film;
import com.entrevueSpringBoot.api.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film getFilmById(Long id){
        Optional<Film> filmOptional = filmRepository.findById(id);
        if(filmOptional.isPresent()){
            return  filmOptional.get();
        }
        else {
            throw new EntityNotFoundException(MessageFormat.format("film with id : {0} does not exists.", id));
        }
    }

    public Film addNewFilm(Film film) {
        Optional<Film> filmOptional = filmRepository.findFilmById(film.getId());
        if(filmOptional.isPresent()){
            throw new IllegalStateException(MessageFormat.format("film with id : {0} is taken.", film.getId()));
        }
        Film savedEntity = filmRepository.save(film);
        return savedEntity;
    }
}
