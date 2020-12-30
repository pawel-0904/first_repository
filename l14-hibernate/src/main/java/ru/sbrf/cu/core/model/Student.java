package ru.sbrf.cu.core.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Student")
public class Student {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;

    // Задает имя и некоторые свойства поля таблицы, на которое будет отображаться поле сущности
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // Указывает на связь между таблицами "один к одному"
    @OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL)
    // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    // Указывает на связь между таблицами "один ко многим"
    @OneToMany(targetEntity = EMail.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private List<EMail> emails;

    // Указывает на связь между таблицами "многие ко многим"
    @ManyToMany(targetEntity = Course.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Задает таблицу связей между таблицами для хранения родительской и связанной сущностью
    @JoinTable(name = "StudentCourse", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;


    public Student(){};

    public Student(String name){
        this.name = name;
    }
    public Student(long id,String name){
        this.name = name;
        this.id = id;
    }

    public Student(long id, String name, Avatar avatar){
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    public Student(long id, String name, Avatar avatar, List<Course> courses){
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
