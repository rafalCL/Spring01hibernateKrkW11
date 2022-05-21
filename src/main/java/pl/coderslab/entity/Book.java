package pl.coderslab.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

// Dla encji Book ustaw następujące ograniczenia:
//title - minimum 5 znaków
//rating - w przedziale 1 do 10
//description - maksymalnie 600 znaków
//author - pole wymagane
//publisher - pole wymagane
//Rozbuduj encję o pole:
//pages - większe od 1
//Utwórz kontroler o nazwie ValidationController.
//Uzupełnij ziarno dla walidacji.
//Sprawdź działanie walidacji w akcji kontrolera dostępnej pod adresem /validate.

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 5)
    private String title;
    @Column(nullable = true)
    @Range(min = 1, max = 10)
    private Integer rating;
    @Column(length = 1000)
    @Size(max = 600)
    private String description;
    @ManyToOne
    @NotNull
    private Publisher publisher;
    @ManyToMany
    @NotNull(message = "Należy podać co najmniej jednego autora")
    @NotEmpty
    private List<Author> authors;
    @Min(1)
    private int pages;

    public Book() {
    }

    public Book(String title, Integer rating, String description, Publisher publisher) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.publisher = publisher;
    }

    private Book(
            long id,
            String title,
            Integer rating,
            String description,
            List<Author> authorsSrc,
            Publisher publisher,
            int pages
    ) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.authors = new ArrayList<>();
        authorsSrc.forEach(author -> authors.add(new Author(author)));
        this.publisher = new Publisher(publisher);
        this.pages = pages;
    }

    public static Book create(Book toCopy) {
        return new Book(
                toCopy.id,
                toCopy.title,
                toCopy.rating,
                toCopy.description,
                toCopy.authors,
                toCopy.publisher,
                toCopy.pages
        );
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return
//                "Book{" +
//                "id=" + id +
                "title='" + title + '\'' +
                        ", rating=" + rating +
                        ", description='" + description + '\'' +
                        ", publisher=" + publisher +
                        ", authors=" + authors +
                        ", pages=" + pages
//                        +             '}'
                ;
    }
}
