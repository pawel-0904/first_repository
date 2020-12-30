package first_project.example.SpringBoot_HomeTask;

import first_project.example.SpringBoot_HomeTask.dao.BooksDao;
import first_project.example.SpringBoot_HomeTask.dao.BooksDaoSimple;
import first_project.example.SpringBoot_HomeTask.service.BooksService;
import first_project.example.SpringBoot_HomeTask.service.BooksServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class SpringBootHomeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHomeTaskApplication.class, args);
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

}
