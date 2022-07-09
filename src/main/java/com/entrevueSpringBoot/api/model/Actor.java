package com.entrevueSpringBoot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Actor {

    @Id
    @SequenceGenerator(
            name="actor_seq",
            sequenceName = "actor_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actor_seq"
    )
    private Long id;

    @JsonProperty("nom")
    private String lastname;

    @JsonProperty("prenom")
    private String firstname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Actor() {
    }

    public Actor(Long id, String lastname, String firstname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Actor(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

}
