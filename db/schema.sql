create table person
(
    id       serial primary key,
    username varchar(255),
    password varchar(255),
    status   boolean default false
);

insert into person (username, password)
values ('admin', '$2a$10$BSmGhQ2XVFOD7YWDOjBTyOHVoOzhA1yw69Pp2fEYXqP73.YQgGiwu');
insert into person (username, password)
values ('admin1', '$2a$10$LAmXtybMFiDujHkTXdN5.e3CpZDiPVV/egC.L4/NpWyu8Hhnksj32');

create table url
(
    encode_url  varchar(255) primary key,
    address_url varchar(255),
    total       int,
    person_id   int references person (id)
);

insert into url (encode_url, address_url, total, person_id)
values ('374f6556', 'https://job4j.ru/profile/exercise/106/task-view/532', 4,
        (select id from person where id = 1));
insert into url (encode_url, address_url, total, person_id)
values ('38f1dba7e', 'https://job4j.ru/profile/exercise/104/task-view/792/solutionId/260783', 1,
        (select id from person where id = 1));
insert into url (encode_url, address_url, total, person_id)
values ('21ec382d', 'https://www.baeldung.com/hibernate-identifiers', 1,
        (select id from person where id = 1));
insert into url (encode_url, address_url, total, person_id)
values ('4b7a2a11', 'https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html#put-K-V-', 0,
        (select id from person where id = 1));

