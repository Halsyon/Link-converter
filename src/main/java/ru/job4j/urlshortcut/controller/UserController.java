package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Person;

import ru.job4j.urlshortcut.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Сделаем контроллер для регистрации пользователя и получения списках всех пользователей системы.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private PersonService personService;
    private BCryptPasswordEncoder encoder;

    public UserController(PersonService personService,
                          BCryptPasswordEncoder encoder) {
        this.personService = personService;
        this.encoder = encoder;
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return StreamSupport.stream(
                this.personService.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.personService.findById(id);
        return new ResponseEntity<Person>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody Person person) {
        if (person.getUsername().isEmpty() || person.getPassword() == null) {
            throw new NullPointerException("Person username and password mustn't be empty!");
        }
        this.personService.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        this.personService.delete(person);
        return ResponseEntity.ok().build();
    }
}
