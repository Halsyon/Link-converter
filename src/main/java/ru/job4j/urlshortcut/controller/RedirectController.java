package ru.job4j.urlshortcut.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.UrlService;


/**
 * Когда сайт отправляет ссылку с кодом в ответ нужно вернуть ассоциированный адрес и статус 302.
 */
@RequestMapping("/redirect")
@RestController
public class RedirectController {

    private static Logger logger = LoggerFactory.getLogger(RedirectController.class);

    private UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * вернуть ассоциированный адрес
     * и статус 302 - HttpStatus.FOUND.
     *
     * @param id
     * @return if exist HttpStatus.TEMPORARY_REDIRECT or HttpStatus.BAD_REQUEST
     */

    @GetMapping("/{id}")
    public ResponseEntity<String> getUrl(@PathVariable String id) {
        logger.info("что пришло {}", id);
        if (id == null) {
            throw new NullPointerException("The id mustn't be empty");
        }
        var rsl = urlService.finById(id).orElse(new Url());
        if (rsl.getAddressUrl() == null) {
            return new ResponseEntity<>(
                    "Please enter a valid id url link !",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rsl.getAddressUrl(), HttpStatus.FOUND);
    }
}
