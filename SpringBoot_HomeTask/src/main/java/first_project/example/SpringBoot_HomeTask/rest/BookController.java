package first_project.example.SpringBoot_HomeTask.rest;

import first_project.example.SpringBoot_HomeTask.domain.Book;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import first_project.example.SpringBoot_HomeTask.repostory.BookRepository;
import first_project.example.SpringBoot_HomeTask.repostory.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping(value = "/*")
public class BookController {

    private final GenreRepository genreRepository;

    private final BookRepository repository;

    @Autowired
    public BookController(BookRepository repository, GenreRepository genreRepository) {
        this.repository = repository;
        this.genreRepository = genreRepository;
    }


    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = (List<Book>) repository.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genreRepository.findAll());;
        //model.addAttribute("authors", book.getAuthor());
        model.addAttribute("book", book); // View name
        return "edit";
        //return null;
    }

    @PostMapping("/edit")
    public String saveBook(
            Book book,
            Model model
                            ) {
        //Genre save = genreRepository.save(book.getGenre());
        Book saved = repository.save(book);
        //model.addAttribute(save);
        model.addAttribute(saved);
        return "redirect:/";
    }
}
