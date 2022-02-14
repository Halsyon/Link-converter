package ru.job4j.url_shortcut.controller;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//@RequestMapping() /convert

/**
 * 3. Регистрация URL.
 *
 * Поле того, как пользователь зарегистрировал свой сайт
 * он может отправлять на сайт ссылки и получать преобразованные ссылки.
 */
@RestController
public class RegUrlLink {

    //    @Autowired
//    StringRedisTemplate redisTemplate;
    private Map<String, String> stringMap = new HashMap<>();
    String string = "";

    @GetMapping("/convert/{id}")
    public String getUrl(@PathVariable String id) {
        var rsl = stringMap.get(id);
        System.out.println("URL Retrieved : " + rsl);
        return rsl;
    }

    @PostMapping("/convert")
    public String createConvert(@RequestBody String url) {
        System.out.println(url);
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );

        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            stringMap.put(id, url);
            System.out.println("Id generated :" + id);
        }
        return string;
    }
}
