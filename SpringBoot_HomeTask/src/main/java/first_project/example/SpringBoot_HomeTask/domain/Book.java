package first_project.example.SpringBoot_HomeTask.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Указывает, что данный класс является сущностью
@Table(name = "book") // Задает имя таблицы, на которую будет отображаться сущность
public class Book {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    // Задает имя и некоторые свойства поля таблицы, на которое будет отображаться поле сущности
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // Пусть книги будут одножанровыми
    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "genre_id")
    private Genre genre;

    // У одной книги может быть несколько комментов
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Book(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Book(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }

    public Book(String name, Genre genre,List<Comment> comments) {
        this.name = name;
        this.genre = genre;
        this.comments = comments;
    }
}
