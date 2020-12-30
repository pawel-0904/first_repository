package first_project.example.SpringBoot_HomeTask.dao;

import first_project.example.SpringBoot_HomeTask.domain.Books;

public interface BooksDao {
    //получить все книги
    Books getAllBooks();
    //добавить книгу
    void addBook(String name);
}
