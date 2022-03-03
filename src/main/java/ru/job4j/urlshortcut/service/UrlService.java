package ru.job4j.urlshortcut.service;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.model.dto.UrlStatistic;
import ru.job4j.urlshortcut.repository.UrlRepo;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Объект Сервис, для работы с Ссылками находящимимся в БД таблица-url
 */
@Service
public class UrlService {

    private static Logger logger = LoggerFactory.getLogger(UrlService.class);

    private UrlRepo urlRepo;

    public UrlService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public Url save(Url url) {
        return urlRepo.save(url);
    }

    public Iterable<Url> findAll() {
        return urlRepo.findAll();
    }

    /**
     * можно получить статистку всех адресов и количество вызовов этого адреса.
     *
     * @return
     */
    public List<UrlStatistic> findAllStatistic() {
        return StreamSupport
                .stream(this.urlRepo.findAll().spliterator(), false)
                .map(url -> new UrlStatistic(url.getAddressUrl(), url.getTotal()))
                .collect(Collectors.toList());
    }

    /**
     * найти ссылку по ключу
     *
     * @param id
     * @return
     */
    public Optional<Url> finById(String id) {
        var url = urlRepo.findById(id).orElse(new Url());
        if (url.getEncodeUrl() != null) {
            urlRepo.setTotal(1, id);
        }
        return Optional.of(url);
    }

    /**
     * шифрование ссылки
     *
     * @param url
     * @return
     */
    public String encodeLink(String url) {
        String id = null;

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );

        if (urlValidator.isValid(url)) {
            id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
        }
        return id;
    }

    public Url putAddressAndUrl(String url, String id) {
        Url urlEnc = new Url();
        urlEnc.setAddressUrl(url);
        urlEnc.setEncodeUrl(id);
        return urlRepo.save(urlEnc);
    }
}
