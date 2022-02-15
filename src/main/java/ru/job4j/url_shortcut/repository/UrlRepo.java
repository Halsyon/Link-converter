package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.model.Url;

/**
 * ключом будет закодированное занчение адреса, значением будет строка
 */
public interface UrlRepo extends CrudRepository<Url, String> {
 }

