package ru.sbrf.cu.core.model;


import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Course(){};

    public Course(long id,String name) {
        this.id = id;
        this.name = name;
    }
}
