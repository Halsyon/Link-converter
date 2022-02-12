create table authoritys
(
    id        serial primary key,
    authority varchar(255)
);

insert into authoritys (authority)
values ('ROLE_USER');
insert into authoritys (authority)
values ('ROLE_ADMIN');
create table users
(
    id           serial primary key,
    enabled      boolean,
    password     varchar(255),
    username     varchar(255),
    authority_id int references authoritys (id)
);

insert into users (username, enabled, password, authority_id)
values ('Ivan Sobolev', true, '$2a$10$TqfYcEWuXxoY6L/aAz2X8egz47vmbtzqAH3PrnWF2gq2vfo7hmbHW',
        (select id from authoritys where authority = 'USER'));
insert into users (username, enabled, password, authority_id)
values ('Svetlana Donovan', true, '$2a$10$HiWxVMtZjCOHyk4ktTzeQee7TR/BBrpnEmtORIi32N6Fh6NBiFpA.',
        (select id from authoritys where authority = 'USER'));
insert into users (username, enabled, password, authority_id)
values ('Sergei Shirokov', true, '$2a$10$CzztUARq9leM95pmfI70OOT.qOrmxqQF.idwN1AOmpKRAMyzkjJsm',
        (select id from authoritys where authority = 'USER'));
insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$NLzdxS.HApEfFa9M1P6sN.qbqDE3trwQnJzkjYBGmGjdqIMu9/dbi',
        (select id from authoritys where authority = 'ADMIN'));

create table posts
(
    id          serial primary key,
    created     timestamp without time zone not null default now(),
    description varchar(255),
    name        varchar(255),
    user_id     int references users (id)
);

insert into posts (description, name, user_id)
values ('Продам велосипед, новый, горный, 25 скоростей Schimano',
        'Продам велосипед', 1);
insert into posts (description, name, user_id)
values ('Куплю или приму в дар мотоцикл, б/у, находу',
        'Куплю мотоцикл', 2);
insert into posts (description, name, user_id)
values ('Требования Java, JS, HTML, PostgreSQL, Git, Linux',
        'Вакансия на стажера', 3);