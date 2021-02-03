package first_project.example.SpringBoot_HomeTask.shell;

import first_project.example.SpringBoot_HomeTask.domain.Author;
import first_project.example.SpringBoot_HomeTask.domain.Book;
import first_project.example.SpringBoot_HomeTask.domain.Comment;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import first_project.example.SpringBoot_HomeTask.repostory.AuthorRepository;
import first_project.example.SpringBoot_HomeTask.repostory.BookRepository;
import first_project.example.SpringBoot_HomeTask.repostory.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private String userName;

    @Autowired
    private BookRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }
    //Добавить книгу
    //Потом надо этод метод доделать -> при добавлении книг через интерфейс
    @ShellMethod(value = "Add Book", key = {"a", "add", "add Book"})
    public void addNewBook(@ShellOption(defaultValue = "The Cruel Age") String bookName) {
        repository.save(new Book(bookName));
    }

    //Получить все книги
    @ShellMethod(value = "Get All Books", key = {"g", "get", "get All"})
    public int getAllBooks() {
        List<Book> allBooks = (List<Book>) repository.findAll();

        for (Book row : allBooks)
        {
            System.out.println("Книга: " + row.getName() + "; Жанр:" + row.getGenre().getGenreName());
        }
        return allBooks.size();
    }

    //Получить все Жанры
    @ShellMethod(value = "Get All Genres", key = {"gg", "getGenres"})
    public void getAllGenres() {

        List<Genre> allGenres = (List<Genre>) genreRepository.findAll();

        for (Genre row : allGenres)
        {
            System.out.println("Жанр: " + row.getGenreName());
        }

    }

    //Поиск книги по имени
    @ShellMethod(value = "Find Book", key = {"f", "find", "find book"})
    public String findBook(String bookName) {

        Book book = repository.findByName(bookName);
        System.out.println("Книга: " + book.getName());
        return book.getName();

    }

    //Поиск книги по имени
    @ShellMethod(value = "Find Book By Id", key = {"fi", "find id", "find book by id"})
    public String findBookById(int id) {

        Optional<Book> book = repository.findById(id);
        System.out.println("Книга: " + book.get().getName());
        return book.get().getName();

    }

    //Изменение книги по id
    @ShellMethod(value = "Change Book By Id", key = {"ci", "change by id", "change book by id"})
    public String changeBookById(int id) {

        Optional<Book> book = repository.findById(id);
        book.get().setName("madeNewName");
        repository.save(book.get());
        System.out.println("Книга: " + book.get().getName());
        return book.get().getName();

    }

    //Изменение жанра книги по id
    @ShellMethod(value = "Change GenreBook By Id", key = {"cg", "change genre", "change genreBook by id"})
    public String changeGenreBookById(int id) {

        Optional<Book> book = repository.findById(id);
        book.get().getGenre().setGenreName("madeNewGenreName");
        repository.save(book.get());
        System.out.println("Книга: " + book.get().getGenre().getGenreName());
        return book.get().getGenre().getGenreName();

    }

    //Поиск книги по жанру
    @ShellMethod(value = "Find By Genre", key = {"fg", "find by genre"})
    public int findByGenre(String genreName) {
        int count = 0;

        //Сначала найдем жанр
        Optional<Genre> genre = genreRepository.findByGenreName(genreName);
        if (genre.isPresent()) {

            //Потом книгу по этому жанру
            List<Book> allBooks = genre.get().getBooks();
            count = allBooks.size();

            for (Book row : allBooks) {
                System.out.println("Книга: " + row.getName() + "; Жанр:" + row.getGenre().getGenreName());
            }
        }
        else {
            System.out.println("Жанр не найден!");
        }
        return count;
    }

    //Поиск книги по автору
    @ShellMethod(value = "Find By Author", key = {"fa", "find by author"})
    public int findByAuthor(String authorName) {
        int count = 0;
        //Сначала найдем автора
        Optional<Author> author = authorRepository.findByFio(authorName);



        if (author.isPresent()) {
            //Потом книгу по этому автору
            List<Book> allBooks = author.get().getBooks();
            count = allBooks.size();

            for (Book row : allBooks) {
                //Соберем все комменты по книге
                List<Comment> comments = row.getComments();
                String allCommentsOfBook = "";
                for (Comment c : comments) {
                    allCommentsOfBook = allCommentsOfBook + c.getComment() + ", ";
                }
                //Соберем авторов книги
                List<Author> authors = row.getAuthor();
                String allAuthorsOfBook = "";
                 for (Author a : authors) {
                     allAuthorsOfBook = allAuthorsOfBook + a.getFio() + ";";
                }

                //надо сделать отдельный метод для вывода
                System.out.println("Книга: " + row.getName() + "; Авторы: " + allAuthorsOfBook+ " Жанр: " + row.getGenre().getGenreName() + "; Комментарии: " + allCommentsOfBook);
                //System.out.println("Книга: " + row.getName() + "; Авторы:" + authoursOfBook + " Жанр:" + row.getGenre().getGenreName());
            }
        }
        else {
            System.out.println("Автор не найден!");
        }
        return count;
    }

    //Поиск книги по названию с помощью like
    @ShellMethod(value = "Find By Like", key = {"fl", "find by like"})
    public int findByLike(String name) {

        List<Book> allBooks = repository.findByNameLike(name);

        for (Book row : allBooks)
        {
            System.out.println("Книга: " + row.getName() + "; Жанр:" + row.getGenre().getGenreName());

        }
        return allBooks.size();
    }

     /*private Availability isPublishEventCommandAvailable() {
         return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
     }*/
}