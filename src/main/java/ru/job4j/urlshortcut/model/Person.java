package ru.job4j.urlshortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * model Person in DB
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NotBlank(message = "username must be not empty")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "password must be not empty")
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private boolean status;

    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Set<Url> urls = new HashSet<>();

    public Person() {
    }

    public static Person of(String username, String password, boolean status) {
        Person person = new Person();
        person.username = username;
        person.password = password;
        person.status = status;
        return person;
    }

    public void addUrl(Url url) {
        this.urls.add(url);
    }
}
