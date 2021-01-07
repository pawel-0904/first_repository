package first_project.example.SpringBoot_HomeTask.repostory;

import com.sun.tools.javah.Gen;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import first_project.example.SpringBoot_HomeTask.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();

    Book findByName(String name);

    Book findByGenre(Optional<Genre> genre);

    //Book findByGenreName(String );

    //Book findByGenreName(String genreName);

}
