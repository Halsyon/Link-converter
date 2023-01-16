package com.linkconverter.controller;

import com.linkconverter.model.dto.Code;
import com.linkconverter.model.dto.Link;
import com.linkconverter.service.PersonService;
import com.linkconverter.service.UrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.linkconverter.model.Url;

/**
 * 3. Регистрация URL.
 * <p>
 * Поле того, как пользователь зарегистрировал свой сайт
 * он может отправлять на сайт ссылки и получать преобразованные ссылки.
 */
@RestController
public class RegUrlLink {

    private PersonService personService;
    private UrlService urlService;

    public RegUrlLink(PersonService personService,
                      UrlService urlService) {
        this.personService = personService;
        this.urlService = urlService;
    }

    /**
     * вернуть обратно по ключу String url
     *
     * @param id url key encoded
     * @return
     */
    @GetMapping("/convert/{id}")
    public ResponseEntity<Link> getUrl(@PathVariable String id) {
        if (id == null) {
            throw new NullPointerException("The id mustn't be empty");
        }

        var rsl = urlService.finById("id").orElse(new Url());
        if (rsl.getAddressUrl() == null) {
            return new ResponseEntity<Link>(
                    new Link("Please enter a valid id url link !"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Link>(new Link(rsl.getAddressUrl()), HttpStatus.FOUND);
    }

    /**
     * Отправляем URL.
     * телом JSON объекта.
     * {url: "https://job4j.ru/profile/exercise/106/task-view/532"}
     * Получаем Ключ - ZRUfD2 ассоциирован с URL.
     * Ответ от сервера.
     * {code: УНИКАЛЬНЫЙ_КОД}
     *
     * @param link
     * @return
     */
    @PostMapping("/convert")
    public ResponseEntity<Code> createConvert(@RequestBody Link link) {
        if (link.getUrl() == null) {
            throw new NullPointerException("Person id mustn't be empty");
        }

        var holder = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );
        String url = link.getUrl();
        if (urlValidator.isValid(url)) {
            var id = urlService.encodeLink(url);
            var u = urlService.save(urlService.putAddressAndUrl(url, id));
            personService.addUrl(holder.toString(), u);
            return new ResponseEntity<>(Code.of(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                Code.of("Please enter a valid address url link !"),
                HttpStatus.BAD_REQUEST);
    }
}
