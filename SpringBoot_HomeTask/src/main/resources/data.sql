insert into genre(genre_Name)
values ('Fiction'), ('TrueStory'), ('genre_03'), ('genre_04'), ('genre_05');

insert into book(name,genre_id)
values ('TheCruelAge', 1), ('FlowersForAlgernon', 2), ('book_03', 3), ('book_04', 4), ('book_05', 4);

insert into comment(comment, book_id)
values ('Супер', 1), ('Такое себе', 1), ('Норм', 2), ('Сойдет', 2), ('comment', 3);

insert into author(fio)
values ('Kalashnikov_I'), ('Kiz_D');

insert into book_author(book_id, author_id)
values (1, 1),   (2, 2);