package com.linkconverter.service;

import com.linkconverter.model.dto.RegstrnStatus;
import org.springframework.stereotype.Service;
import com.linkconverter.model.Person;
import com.linkconverter.model.Url;
import com.linkconverter.repository.PersonRepo;

import java.util.Optional;

/**
 * Service Person object
 */
@Service
public class PersonService {

    private PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person save(Person person) {
        person.setStatus(true);
        return personRepo.save(person);
    }

    public Iterable<Person> findAll() {
        return personRepo.findAll();
    }

    public Optional<Person> findById(int id) {
        return findById(id);
    }

    public void delete(Person person) {
        personRepo.delete(person);
    }

    public Person findByName(String name) {
        return personRepo.findPersonByUsername(name);
    }

    public Person addUrl(String person, Url url) {
        var p = personRepo.findPersonByUsername(person);
        p.addUrl(url);
        return personRepo.save(p);
    }

    public RegstrnStatus addPersonToRegstrSt(Person person) {
        RegstrnStatus regstrnStatus  = new RegstrnStatus();
        regstrnStatus.setRegistration(person.isStatus());
        regstrnStatus.setLogin(person.getUsername());
        regstrnStatus.setPassword(person.getPassword());
        return regstrnStatus;
    }
}
