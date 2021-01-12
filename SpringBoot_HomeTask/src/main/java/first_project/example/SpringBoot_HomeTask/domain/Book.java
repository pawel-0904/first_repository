package first_project.example.SpringBoot_HomeTask.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity // Указывает, что данный класс является сущностью
@Table(name = "book") // Задает имя таблицы, на которую будет отображаться сущность
public class Book {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    @NonNull
    // Задает имя и некоторые свойства поля таблицы, на которое будет отображаться поле сущности
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // Пусть книги будут одножанровыми
    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.MERGE) // после того как ставлю здесь merge,
    //имя книги может быть не уникальным, поэтому в схеме sql прописал constraint UC_Book UNIQUE (name)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "genre_id")
    private Genre genre;

    // У одной книги может быть несколько комментов
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;

    // Указывает на связь между таблицами "многие ко многим"
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Задает таблицу связей между таблицами для хранения родительской и связанной сущностью
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> author;

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