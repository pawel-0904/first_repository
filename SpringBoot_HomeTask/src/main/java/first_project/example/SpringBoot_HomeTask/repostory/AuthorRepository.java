package first_project.example.SpringBoot_HomeTask.repostory;

import first_project.example.SpringBoot_HomeTask.domain.Author;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends CrudRepository<Author, Integer> {

    List<Author> findAll();

    Optional<Author> findByFio(String fio);

}