package first_project.example.SpringBoot_HomeTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fio", nullable = false, unique = true)
    private String fio;

    public Author(String fio) {
        this.fio = fio;
    }
    public Author( ) {
    }

    public String getFio() {
        return fio;
    }
}

