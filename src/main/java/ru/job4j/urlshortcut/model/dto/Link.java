package ru.job4j.urlshortcut.model.dto;

import lombok.Data;

/**
 * Объект DTO ссылка приходящая для конвертирования
 */
@Data
public class Link {

    private String url;

    public Link() {
    }

    public Link(String url) {
        this.url = url;
    }
}
