package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;

/**
 * ключом будет закодированное занчение адреса, значением будет строка
 */
public interface UrlRepo extends CrudRepository<Url, String> {
 }

