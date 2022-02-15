package ru.job4j.urlshortcut.model.dto;

import lombok.Data;

/**
 * Отчет о регистрации сайта DTO Object
 * Флаг registration указывает, что регистрация выполнена или нет,
 * то есть сайт уже есть в системе.
 */
@Data
public class RegstrnStatus {

    private boolean registration;
    private String login;
    private String password;

}
