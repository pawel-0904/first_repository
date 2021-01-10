package first_project.example.SpringBoot_HomeTask;

import first_project.example.SpringBoot_HomeTask.domain.Book;
import first_project.example.SpringBoot_HomeTask.shell.ApplicationEventsCommands;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(ApplicationEventsCommands.class)

class SpringBootHomeTaskApplicationTests {
	@Autowired
	ApplicationEventsCommands applicationEventsCommands;

	/*@Test
	void contextLoads() {
	}*/
	@DisplayName("Должен вернуть название книги - ищем по названию")
	@Test
	void shouldFindBookByName() {

		Assert.assertEquals("TheCruelAge", applicationEventsCommands.findBook("TheCruelAge"));

	}

	@DisplayName("Должен вернуть кол-во книг - ищем по названию с помощью Like")
	@Test
	void shouldFindBookByLike() {

		Assert.assertEquals(1, applicationEventsCommands.findByLike("Cruel"));

	}

	@DisplayName("Должен вернуть кол-во книг - ищем по жанру")
	@Test
	void shouldFindBookByGenre() {

		Assert.assertEquals(1, applicationEventsCommands.findByGenre("Fiction"));

	}
	@DisplayName("Должен вернуть кол-во всех книг")
	@Test
	void shouldFindAllBook() {

		Assert.assertEquals(6, applicationEventsCommands.getAllBooks());

	}


}
