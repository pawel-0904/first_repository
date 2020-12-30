package first_project.example.SpringBoot_HomeTask.service;

import first_project.example.SpringBoot_HomeTask.domain.Books;

public interface BooksService {
    //получить все книги
    Books getAllBooksService();
    //добавить книгу
    void addBookService(String name);

}
