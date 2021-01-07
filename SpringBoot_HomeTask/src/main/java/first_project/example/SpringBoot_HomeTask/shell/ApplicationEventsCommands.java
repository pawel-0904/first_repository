package first_project.example.SpringBoot_HomeTask.shell;

import first_project.example.SpringBoot_HomeTask.domain.Book;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import first_project.example.SpringBoot_HomeTask.repostory.BookRepository;
import first_project.example.SpringBoot_HomeTask.repostory.GenreRepository;
import first_project.example.SpringBoot_HomeTask.service.BooksServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import first_project.example.SpringBoot_HomeTask.dao.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private BooksDaoSimple booksDaoSimple = new BooksDaoSimple();

    private BooksServiceImpl booksService = new BooksServiceImpl(booksDaoSimple);

    private String userName;

    @Autowired
    private BookRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Add Book", key = {"a", "add", "add Book"})
    public void addNewBook(@ShellOption(defaultValue = "The Cruel Age") String bookName) {
        //booksService.addBookService(bookName);
       // booksService.save(new Book(bookName));
        repository.save(new Book(bookName));

    }

    //Получить все книги
    @ShellMethod(value = "Get All Books", key = {"g", "get", "get All"})
    public String getAllBooks() {
        //Вывод ужасный, пока скорее как заглушка

        List<Book> allBooks = repository.findAll();

        for (Book row : allBooks)
        {
            System.out.println("Книга: " + row.getName() + "; Жанр:" + row.getGenre().getGenreName());
        }

        return "Books " + booksService.getAllBooksService().listBooks;
    }

    //Поиск книги по имени
    @ShellMethod(value = "Find Book", key = {"f", "find", "find book"})
    public void findBook(String bookName) {

        Book book = repository.findByName(bookName);

        System.out.println("      " + book.getName());

    }

    //Поиск книги по жанру
    @ShellMethod(value = "Find By Genre", key = {"fg", "find by genre"})
    public void findByGenre(String genreName) {

        //Сначала найдем жанр
        Optional<Genre> genre = genreRepository.findByGenreName(genreName);

        //Потом книгу по этому жанру
        //Так и не понял, почему я не могу искать по новому объекту (new Genre("TrueStory"))
        Book book = repository.findByGenre(genre);

        System.out.println("      " + book.getName());

    }

    /*private Availability isPublishEventCommandAvailable() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }*/
}
