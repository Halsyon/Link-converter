package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.model.Person;

public interface PersonRepo extends CrudRepository<Person, Integer> {
    public Person findPersonByUsername(String name);
}
