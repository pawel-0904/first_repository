package first_project.example.SpringBoot_HomeTask.repostory;

import first_project.example.SpringBoot_HomeTask.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

   Iterable<Comment> findAll();

    Comment findByComment(String s);

}
