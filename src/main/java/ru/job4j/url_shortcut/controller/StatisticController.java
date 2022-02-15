package ru.job4j.url_shortcut.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.model.dto.UrlStatistic;
import ru.job4j.url_shortcut.service.UrlService;

import java.util.List;

/**
 * После авторизации,
 * пользователь может получить данные всех адресов и колличество обращения к ним
 */
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private UrlService urlService;

    public StatisticController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * можно получить статистку всех адресов и количество вызовов этого адреса.
     * @return
     */
    @GetMapping("/")
    public List<UrlStatistic> findAllStatistic() {
        return urlService.findAllStatistic();
    }
}
