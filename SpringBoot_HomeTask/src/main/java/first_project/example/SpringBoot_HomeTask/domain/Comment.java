package first_project.example.SpringBoot_HomeTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment", nullable = false, unique = true)
    private String comment;

    public Comment(String text) {
        this.comment = text;
    }
}
