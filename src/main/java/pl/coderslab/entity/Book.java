package pl.coderslab.entity;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private Integer rating;
    @Column(length = 1000)
    private String description;
}
