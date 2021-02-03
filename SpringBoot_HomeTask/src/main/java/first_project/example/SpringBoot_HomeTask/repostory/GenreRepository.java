package first_project.example.SpringBoot_HomeTask.repostory;

import first_project.example.SpringBoot_HomeTask.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import first_project.example.SpringBoot_HomeTask.domain.Book;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Integer> {



   Optional<Genre> findByGenreName(String name);

}
