package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url_shortcut.model.Person;
import ru.job4j.url_shortcut.model.dto.RegstrnStatus;
import ru.job4j.url_shortcut.service.PersonService;

/**
 * Registration new Person/user
 */
@RestController
public class RegistrationController {

    private PersonService personService;
    private BCryptPasswordEncoder encoder;

    public RegistrationController(PersonService personService,
                                  BCryptPasswordEncoder encoder) {
        this.personService = personService;
        this.encoder = encoder;
    }

    /**
     * Регистрация в системе
     * registration : true/false
     * Флаг registration указывает, что регистрация выполнена или нет, то есть сайт уже есть в системе.
     *
     * @param person
     * @return
     */
    @PostMapping("/registration")
    public ResponseEntity<RegstrnStatus> registration(@RequestBody Person person) {
        RegstrnStatus regstrnStatus = new RegstrnStatus();
        regstrnStatus.setLogin(person.getUsername());
        regstrnStatus.setPassword(person.getPassword());
        if (person.getUsername().isEmpty() || person.getPassword() == null) {
            throw new NullPointerException("Person username and password mustn't be empty!");
        }
        var userUnique = personService.findByName(person.getUsername());
        if (userUnique != null) {
            regstrnStatus.setRegistration(false);
            return new ResponseEntity<>(regstrnStatus, HttpStatus.BAD_REQUEST);
        }
        person.setPassword(encoder.encode(person.getPassword()));
        personService.save(person);
        regstrnStatus.setRegistration(true);
        System.out.println("после сохранения в БД : " + regstrnStatus);
        return new ResponseEntity<>(
                regstrnStatus,
                HttpStatus.CREATED
        );
    }
}
