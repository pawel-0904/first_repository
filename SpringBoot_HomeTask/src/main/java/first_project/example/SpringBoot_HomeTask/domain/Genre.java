package first_project.example.SpringBoot_HomeTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "genreName", nullable = false, unique = true)
    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public Genre(String genreName){
        this.genreName = genreName;
    }
    public Genre(){
    }

    public String getName() {
        return genreName;
    }
}