package ru.job4j.url_shortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Person {

    @EqualsAndHashCode.Include
    private int id;

    private String username;

    private String password;

    public Person() {
    }
}
