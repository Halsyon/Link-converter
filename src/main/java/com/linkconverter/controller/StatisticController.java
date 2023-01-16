package com.linkconverter.controller;

import com.linkconverter.model.dto.UrlStatistic;
import com.linkconverter.service.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * После авторизации,
 * пользователь может получить данные всех адресов и количество обращения к ним
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
