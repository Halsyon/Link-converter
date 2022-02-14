package ru.job4j.url_shortcut.model.dto;

import lombok.Data;

/**
 * Отчет о регистрации сайта DTO Object
 * Флаг registration указывает, что регистрация выполнена или нет,
 * то есть сайт уже есть в системе.
 */
@Data
public class Registration {

    private int id;
    private boolean status;
    private String login;
    private String password;

}
