package first_project.example.SpringBoot_HomeTask.shell;

import first_project.example.SpringBoot_HomeTask.domain.Book;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import first_project.example.SpringBoot_HomeTask.repostory.BookRepository;
import first_project.example.SpringBoot_HomeTask.repostory.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {


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
        //Genre newGenre = new Genre("NewGenre");
        repository.save(new Book(bookName));

    }

    //Получить все книги
    @ShellMethod(value = "Get All Books", key = {"g", "get", "get All"})
    public void getAllBooks() {

        List<Book> allBooks = repository.findAll();

        for (Book row : allBooks)
        {
            System.out.println("Книга: " + row.getName() + "; Жанр:" + row.getGenre().getGenreName());
        }
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
        Book book = repository.findByGenre(genre);

        System.out.println("      " + book.getName());

    }

    //Поиск книги по названию с помощью like
    @ShellMethod(value = "Find By Like", key = {"fl", "find by like"})
    public void findByLike(String name) {

        List<Book> allBooks = repository.findByNameLike(name);

        for (Book row : allBooks)
        {
            System.out.println("Книга: " + row.getName() + "; Жанр:" + row.getGenre().getGenreName());
        }

    }

    /*private Availability isPublishEventCommandAvailable() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }*/
}
