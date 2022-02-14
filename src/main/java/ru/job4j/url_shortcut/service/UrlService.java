package ru.job4j.url_shortcut.service;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.model.dto.Url;
import ru.job4j.url_shortcut.repository.UrlRepo;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UrlService {

    private UrlRepo urlRepo;

    public UrlService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public Url save(Url url) {
        return urlRepo.save(url);
    }

//сли ключ число
//    public Optional<Url> finById(int id) {
//        return urlRepo.findById(id);
//    }

    public Optional<Url> finById(String id) {
        return urlRepo.findById(id);
    }

    public String encodeLink(String url) {
        String id = null;
//        Url urlEnc = new Url();
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );
//        String url = link.getUrl(); //****TODO
        if (urlValidator.isValid(url)) {
           id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
//            stringMap.put(id, url);
            System.out.println("Id generated :" + id);
//            urlEnc.setAddressUrl(url);
//            urlEnc.setEncodeUrl(id);
//            var u = urlRepo.save(urlEnc);
//            var p = personService.addUrl(holder.toString(), u);
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
