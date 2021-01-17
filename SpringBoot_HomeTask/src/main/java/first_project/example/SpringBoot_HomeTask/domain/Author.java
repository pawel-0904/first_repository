package first_project.example.SpringBoot_HomeTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fio", nullable = false, unique = true)
    private String fio;


    @ManyToMany(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // Задает таблицу связей между таблицами для хранения родительской и связанной сущностью
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

}
