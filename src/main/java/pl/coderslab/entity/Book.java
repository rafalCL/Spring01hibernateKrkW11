package pl.coderslab.entity;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private Integer rating;
    @Column(length = 1000)
    private String description;
    @ManyToOne
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, Integer rating, String description, Publisher publisher) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", publisher=" + publisher +
                '}';
    }
}
