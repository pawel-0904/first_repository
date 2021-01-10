create table genre(
    id bigserial,
    genre_Name varchar(255),
    primary key (id),
    constraint UC_genre_Name UNIQUE (genre_Name)
);

create table author(
    id bigserial,
    fio varchar(255),
    primary key (id)
);

create table book(
    id bigserial,
    name varchar(255),
    genre_id bigint references genre (id),
    primary key (id),
    constraint UC_book UNIQUE (name)
);

create table comment(
    id bigserial,
    comment varchar(2000),
    book_id bigint references book(id) on delete cascade,
    primary key (id)
);

create table book_author(
    book_id bigint references book(id) on delete cascade,
    author_id bigint references author(id),
    primary key (book_id, author_id)
);