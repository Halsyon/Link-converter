package ru.job4j.urlshortcut.model.dto;

import lombok.Data;

/**
 * Объект DTO бля предоставления статистки по колличество запросов по определенной строке
 */
@Data
public class UrlStatistic {

    private String url;

    private Integer total;

    public UrlStatistic() {
    }

    public UrlStatistic(String addressUrl, Integer total) {
        this.url = addressUrl;
        this.total = total;
    }
}
