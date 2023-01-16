package com.linkconverter.repository;

import org.springframework.data.repository.CrudRepository;
import com.linkconverter.model.Person;

public interface PersonRepo extends CrudRepository<Person, Integer> {
    public Person findPersonByUsername(String name);
}
