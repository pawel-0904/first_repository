package first_project.example.SpringBoot_HomeTask.repostory;

import first_project.example.SpringBoot_HomeTask.domain.Author;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import first_project.example.SpringBoot_HomeTask.domain.Book;
import org.springframework.stereotype.Component;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@Component
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();

    Book findByName(String name);

    List<Book> findByGenre(Optional<Genre> genre);

    @Query("SELECT b FROM Book b WHERE b.name LIKE CONCAT('%',:name,'%')")
    List<Book> findByNameLike(@Param("name") String name);

    List<Book> findByAuthor(Optional<Author> author);

    //Book findByGenreName(String );

    //Book findByGenreName(String genreName);

}
