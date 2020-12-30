package ru.sbrf.cu.core.model;

import javax.persistence.*;

@Entity
@Table(name = "StudentCourse")
public class StudentCourse {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    @Column(name = "student_id", nullable = false, unique = false)
    private String student;

    @Column(name = "course_id", nullable = false, unique = false)
    private String course;
}
