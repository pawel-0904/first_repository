package ru.sbrf.cu.core.model;

import javax.persistence.*;

@Entity
@Table
public class EMail {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    @Column(name = "student_id", nullable = false, unique = false)
    private String student;

    @Column(name = "email", nullable = true, unique = true)
    private String email;
}
