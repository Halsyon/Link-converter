package ru.job4j.url_shortcut.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.job4j.url_shortcut.model.Person;

import javax.persistence.*;

/**
 * URL Поле того, как пользователь зарегистрировал свой сайт,
 * он может отправлять на сайт ссылки и получать преобразованные ссылки.
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "url")
public class Url {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Integer id;
    @Id
    @Column(name = "encode_url")
    private String encodeUrl;

    @Column(name = "address_url")
    private String addressUrl;

//    @GeneratedValue(strategy = GenerationType.SEQUENCE) //В сервисе считается количество вызовов каждого адреса.?
   @Version
    @Column(name = "total")
    private Integer total;

    public Url() {
    }
}
