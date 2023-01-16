# Link converter

[![Build Status](https://app.travis-ci.com/Halsyon/Link-converte.svg?branch=master)](https://app.travis-ci.com/Halsyon/Link-converte)
![GitHub top language](https://img.shields.io/github/languages/top/Halsyon/Link-converte?logo=java&logoColor=red)
![GitHub last commit](https://img.shields.io/github/last-commit/Halsyon/Link-converte?logo=github)

Приложение Сервис - Link converter.

Чтобы обеспечить безопасность пользователей, все ссылки на сайте заменяются ссылками на наш сервис.
 
Сервис работает через REST API.

![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_1.jpg)


Сервис позволяет обеспечить безопасность пользователей на сайте,
за счет замены обычных ссылок на конвертированные.
Также проект, позволяет для зарегистрированных в системе сервисом/пользователей производить просмотр
собранной статистики посещений страниц различных сайтов,

Это веб-приложение реализует поле сайта с использованием принципа архитектуры RESTfull.

Used technologies
______________________________________________
- Java Core
- JWT
- Rest API
- Spring Boot Security
- Spring Boot Data JPA
- Spring Boot Web
- Spring Boot Tomcat
- PostgreSQl  
- Maven
- Travis C.I.


![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_9.jpg)

![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_8.jpg)

В приложении будет несколько запросов, функционал:

1. Регистрация сайта.

Сервисом могут пользоваться разные сайты. Каждому сайту выдается пару пароль и логин.
Чтобы зарегистрировать сайт в систему нужно отправить запрос.
URL

POST /registration
в случае если пользователь регистрируется впервые
![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_2.jpg)

Ответ от сервера, в случае если такой пользователь уже существует

![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_3.jpg)

2. Авторизация.

Авторизацию реализована с помощью JWT. 
Пользователь отправляет POST запрос с login и password и получает ключ.

Этот ключ возвращается в блоке HEAD.

![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_4.jpg)

3. Регистрация URL.

Поле того, как пользователь зарегистрировал свой сайт он может отправлять на сайт ссылки
и получать преобразованные ссылки.

Отправляем URL. POST /convert
Получаем. Ответ от сервера - уникальный код.

![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_5.jpg)

Ключ 4b7a2a11 ассоциирован с URL в БД

4. Переадресация. Выполняется без авторизации.

Когда сайт отправляет ссылку с кодом в ответ можно вернуть ассоциированный адрес и статус 302.
Ответ от сервера в заголовке. HTTP CODE - 302 REDIRECT URL

GET /redirect/УНИКАЛЬНЫЙ_КОД

![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_6.jpg)

5. Статистика.

В сервисе считается количество вызовов каждого адреса.
По сайту можно получить статистку всех адресов и количество вызовов этого адреса.

Вызов. GET /statistic
Ответ от сервера JSON.

![Image of Arch](https://github.com/Halsyon/Link-converte/blob/master/image/Screenshot_7.jpg)






