package first_project.example.SpringBoot_HomeTask.dao;

import first_project.example.SpringBoot_HomeTask.domain.Books;
import org.springframework.stereotype.Component;

//Можно раскомментить, но тогда убать bean
@Component
public class BooksDaoSimple implements BooksDao{
    Books books = new Books();

    @Override
    public Books getAllBooks() {
        return books;
    }

    @Override
    public void addBook(String name) {
        books.listBooks.add(name);
    }
}
