package com.entrevueSpringBoot.api.repository;

import com.entrevueSpringBoot.api.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film  f WHERE f.id = ?1")
    Optional<Film> findFilmById(Long id);

}
