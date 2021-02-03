package first_project.example.SpringBoot_HomeTask.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "genre_Name", nullable = false, unique = true)
    private String genreName;

    // Один и тот же жанр у нескольких книг
    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private List<Book> books;

    public Genre(String genreName){
        this.genreName = genreName;
    }

}

