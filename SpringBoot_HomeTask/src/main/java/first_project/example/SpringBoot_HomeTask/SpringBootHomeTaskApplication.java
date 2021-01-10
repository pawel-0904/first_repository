package first_project.example.SpringBoot_HomeTask;

import first_project.example.SpringBoot_HomeTask.domain.Book;
import first_project.example.SpringBoot_HomeTask.domain.Comment;
import first_project.example.SpringBoot_HomeTask.domain.Genre;
import first_project.example.SpringBoot_HomeTask.repostory.BookRepository;
import first_project.example.SpringBoot_HomeTask.repostory.GenreRepository;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@SpringBootApplication
public class SpringBootHomeTaskApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(SpringBootHomeTaskApplication.class, args);
		//Console.main(args);
	}
	//Заккоментил, так как повесил аннотацию на BooksDaoSimple
	/*@Bean
	public BooksDao booksDao(){
		return new BooksDaoSimple();
	}*/

	/*@Bean
	public BooksService booksService(BooksDao booksDao) {
		return new BooksServiceImpl(booksDao);
	}*/

	@Autowired
	private BookRepository repository;

	@Autowired
	private GenreRepository genreRepository;

	@PostConstruct
	public void init() {
		Genre genreFiction = new Genre("NonFiction");
		genreRepository.save(genreFiction);
		//genreRepository.save(new Genre("Fiction"));
		Comment comment1 = new Comment("Супер");
		Comment comment2 = new Comment("Такое себе");

		List<Comment> comments = new ArrayList<>();
		comments.add(comment1);
		comments.add(comment2);
		//repository.save(new Book("The Cruel Age", genreFiction, comments));

		//repository.save(new Book("The Cruel Age", genreFiction));
		//repository.save(new Book("The Cruel Age 2", new Genre("Fiction")));
		repository.save(new Book("The New Book", genreFiction));

		//Если раскоментить эту строчку, то будет ошибка - так и не понял почему.
		//Почему-то не могу сделать две разных книги с одним жанром
		//repository.save(new Book("Flowers for Algernon", genreFiction));

		//repository.save(new Book("FlowersForAlgernon", new Genre("TrueStory")));


		//Book book = repository.findByName("The Cruel Age");
		//System.out.println("-------------//---------//--------//-----" + book.getName());



	}

}
