package first_project.example.SpringBoot_HomeTask;

import first_project.example.SpringBoot_HomeTask.repostory.BookRepository;
import first_project.example.SpringBoot_HomeTask.repostory.GenreRepository;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Configuration
@SpringBootApplication
public class SpringBootHomeTaskApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(SpringBootHomeTaskApplication.class, args);
		////Console.main(args);
	}

	@Autowired
	private final BookRepository repository;

	@Autowired
	private final GenreRepository genreRepository;

	public SpringBootHomeTaskApplication(BookRepository repository, GenreRepository genreRepository) {
		this.repository = repository;
		this.genreRepository = genreRepository;
	}

	@PostConstruct
	public void init() {


	}

}
