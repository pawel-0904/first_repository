package first_project.example.SpringBoot_HomeTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "genre_Name", nullable = false, unique = true)
    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public Genre(String genreName){
        this.genreName = genreName;
    }
    public Genre(){
    }

    /*public Genre(long id,String genreName){
        this.id = id;
        this.genreName = genreName;
    }*/

    /*@OneToMany (mappedBy="genre", fetch=FetchType.EAGER)
    private List<Book> books;*/

    public String getName() {
        return genreName;
    }
}

