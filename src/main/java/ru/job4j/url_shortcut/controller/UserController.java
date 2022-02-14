package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url_shortcut.model.Person;
import ru.job4j.url_shortcut.model.dto.RegstrnStatus;
import ru.job4j.url_shortcut.repository.UserStore;
import ru.job4j.url_shortcut.service.PersonService;

import java.util.List;

/**
 * Сделаем контроллер для регистрации пользователя и получения списках всех пользователей системы.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private PersonService personService;
    private UserStore users;
    private BCryptPasswordEncoder encoder;

//    public UserController(UserStore users,
//                          BCryptPasswordEncoder encoder) {
//        this.users = users;
//        this.encoder = encoder;
//    }

    public UserController(PersonService personService,
                          UserStore users,
                          BCryptPasswordEncoder encoder) {
        this.personService = personService;
        this.users = users;
        this.encoder = encoder;
    }

    //    @PostMapping("/sign-up")
    // венуть объект Registration
    @PostMapping("/registration")
    public ResponseEntity<RegstrnStatus> registration(@RequestBody Person person) {
        RegstrnStatus regstrnStatus = new RegstrnStatus();
//        var userUnique = personService.findByName(person.getUsername());
//        if (userUnique != null) {
//            String errorMessage = null;
//            errorMessage = "A user with the same name already exists,\n"
//                    + " username must be unique !!!";
//           /* model.addAttribute("errorMessage", errorMessage);
//            return "/reg";*/
//            regstrnStatus.setRegistration(userUnique.isStatus());
//            regstrnStatus.setLogin(userUnique.getUsername());
//            return new ResponseEntity<RegstrnStatus>(regstrnStatus, HttpStatus.BAD_REQUEST);
//        }

//    public void signUp(@RequestBody Person person) {

        person.setPassword(encoder.encode(person.getPassword()));
        System.out.println("DO save :" + person);
//TODO поменять на запись в БД
        var rslPerson = users.save(person);
        System.out.println("RESPONSE REGIST : " + regstrnStatus);
        regstrnStatus.setRegistration(rslPerson.isStatus());
        regstrnStatus.setLogin(rslPerson.getUsername());
        regstrnStatus.setPassword(rslPerson.getPassword());
        return new ResponseEntity<RegstrnStatus>(
//                rslPerson.orElse(new Employee()),
//                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
//        );
                regstrnStatus,
                HttpStatus.OK
        );
    }

// TODO ???
    @GetMapping("/all")
    public List<Person> findAll() {
        return users.findAll();
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
//    @Validated(Operation.OnUpdate.class)    + update(@Valid @RequestBody Person person) {
    public ResponseEntity<Void> update(@RequestBody Person person) {
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
