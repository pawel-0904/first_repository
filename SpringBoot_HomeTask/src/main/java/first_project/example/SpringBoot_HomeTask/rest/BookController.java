package first_project.example.SpringBoot_HomeTask.rest;

import first_project.example.SpringBoot_HomeTask.domain.Author;
import first_project.example.SpringBoot_HomeTask.domain.Book;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import first_project.example.SpringBoot_HomeTask.repostory.AuthorRepository;
import first_project.example.SpringBoot_HomeTask.repostory.BookRepository;
import first_project.example.SpringBoot_HomeTask.repostory.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class BookController {

    private final GenreRepository genreRepository;

    private final BookRepository repository;

    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository repository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }


    @GetMapping("/")
    public String listPage(@RequestParam(required= false, defaultValue="") Genre genre, Model model) {
        if(genre != null)
        {
            model.addAttribute("books",repository.findByGenre(genre));
        }else {
            model.addAttribute("books", repository.findAll());
        }
        model.addAttribute("genres", (List<Genre>) genreRepository.findAll());
        model.addAttribute("filter_genres", model.getAttribute("genres"));
        return "list";
    }

    @GetMapping("/filter")
    public String filter (@ModelAttribute("genre") Genre genre, Model model){
        return this.listPage(genre,model);
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        List<Genre> genres = (List<Genre>) genreRepository.findAll();
        List<Author> authors = (List<Author>) authorRepository.findAll();

        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("book", book); // View name

        return "edit";
    }

    @GetMapping("/comment/add")
    public String addComment(@RequestParam("id") int id,Model model){
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book); // View name
        return "addcomment";
    }

    @PostMapping("/comment/add")
   public String addComment (@ModelAttribute(value = "comment") String newComment, Model model, Book book){
        //Возьмем ту самую книгу, в которую потом добавим новый коммент
        Book bookForComment = repository.findById(book.getId()).orElseThrow(NotFoundException::new);
        bookForComment.addComment(newComment);
        Book saved = repository.save(bookForComment);
        model.addAttribute(saved);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String saveBook(
            Book book,
            Model model,
            Genre genre,
            Author author
                            ) {
        Book saved = repository.save(book);
        model.addAttribute(saved);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteBook (@RequestParam("id") int id, Model model){
        repository.deleteById(id);
        return "redirect:/";
    }
}
