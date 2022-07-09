package com.entrevueSpringBoot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Film {
    @Id
    @SequenceGenerator(
            name="film_seq",
            sequenceName = "film_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "film_seq"
    )
    private Long id;

    @JsonProperty("titre")
    private String title;

    private String description;

    @JsonProperty("acteurs")
    @OneToMany(targetEntity=Actor.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Actor> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Film() {
    }

    public Film(Long id, String title, String description, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.actors = actors;
    }

    public Film(String title, String description, List<Actor> actors) {
        this.title = title;
        this.description = description;
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", actors=" + actors +
                '}';
    }
}
