package first_project.example.SpringBoot_HomeTask.shell;

import first_project.example.SpringBoot_HomeTask.service.BooksServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import first_project.example.SpringBoot_HomeTask.dao.*;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private BooksDaoSimple booksDaoSimple = new BooksDaoSimple();

    private BooksServiceImpl booksService = new BooksServiceImpl(booksDaoSimple);

    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Add Book", key = {"a", "add", "add Book"})
    public void addNewBook(@ShellOption(defaultValue = "Azbuka") String bookName) {
        booksService.addBookService(bookName);

    }

    @ShellMethod(value = "Get All Books", key = {"g", "get", "get All"})
    public String getAllBooks() {
        //Вывод ужасный, пока скорее как заглушка
        return "Books " + booksService.getAllBooksService().listBooks;
    }

    /*private Availability isPublishEventCommandAvailable() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }*/
}
