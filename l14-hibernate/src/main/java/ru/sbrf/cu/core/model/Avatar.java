package ru.sbrf.cu.core.model;

import javax.persistence.*;

@Entity
@Table(name = "Avatar")
public class Avatar {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    //@OneToOne(mappedBy = "Avatar")
    private long id;

    @Column(name = "photo_url", nullable = true, unique = true)
    private String photo_url;

    public Avatar(){};

    public Avatar(long id,String photo_url) {
        this.id = id;
        this.photo_url = photo_url;
    }
}

