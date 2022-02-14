package ru.job4j.url_shortcut.repository;

import org.springframework.stereotype.Component;
import ru.job4j.url_shortcut.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {
    private final ConcurrentHashMap<String, Person> users = new ConcurrentHashMap<>();

    public Person save(Person person) {
        person.setStatus(true);
        users.put(person.getUsername(), person);
        System.out.println("PERSON SAVE : " + person);
        return person;
    }


    public Person findByUsername(String username) {
        return users.get(username);
    }

    public List<Person> findAll() {
        return new ArrayList<>(users.values());
    }
}
