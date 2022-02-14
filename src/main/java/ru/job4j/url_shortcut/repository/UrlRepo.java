package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.model.dto.Url;

import java.util.Optional;

/**
 * если ключ будет строка
 */
public interface UrlRepo extends CrudRepository<Url, String> {
//    public int iteration ();
}
//public interface UrlRepo extends CrudRepository<Url, Integer> {
//}
