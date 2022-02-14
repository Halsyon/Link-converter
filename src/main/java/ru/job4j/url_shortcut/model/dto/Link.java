package ru.job4j.url_shortcut.model.dto;

import lombok.Data;

@Data
public class Link {

    private String url;

    public Link() {
    }

    public Link(String url) {
        this.url = url;
    }
}
