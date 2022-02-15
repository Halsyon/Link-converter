package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Person;

public interface PersonRepo extends CrudRepository<Person, Integer> {
    public Person findPersonByUsername(String name);
}
