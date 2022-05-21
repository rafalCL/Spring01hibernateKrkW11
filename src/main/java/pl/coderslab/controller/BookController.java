package pl.coderslab.controller;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final PublisherDao publisherDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(PublisherDao publisherDao, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.publisherDao = publisherDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    // example request GET http://localhost:8080/hiber/books/add?title=Thinking+in+Java&rating=10&description=best+book&publisherId=1
    @GetMapping("/add")
    @ResponseBody
    public String addBook(@RequestParam final String title,
                          @RequestParam final Integer rating,
                          @RequestParam final String description,
                          @RequestParam(required = false) final Long publisherId) {
        Publisher publisher = null;
        if (publisherId != null) {
            publisher = publisherDao.findById(publisherId);
        }

        Book book = new Book(title, rating, description, publisher);

        bookRepository.save(book);
        return "id=" + book.getId();
    }

    @GetMapping("")
    @ResponseBody
    public String filter(@RequestParam(required = false) Integer minRating) {
        log.info("Handling request, params: minRating={}", minRating);

        final List<Book> books = new ArrayList<>();
        if (minRating == null) {
            books.addAll(bookRepository.findAll());
        } else {
            books.addAll(bookRepository.findByRatingGreaterThanEqual(minRating));
        }
        final String html = books.stream()
                .map(Book::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));

        return html;
    }

    @GetMapping("/bycatid")
    @ResponseBody
    @Transactional
    public String filterByCatId(@RequestParam Long categoryId) {
        final List<Book> books = bookRepository.findAllByCategoryId(categoryId);

        final String html = books.stream()
                .map(book -> {
                    final Book copy = Book.create(book);
                    Hibernate.initialize(copy.getAuthors());
                    return copy;
                })
                .map(Book::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));

        return html;
    }

    @GetMapping("/bycategory")
    @ResponseBody
    @Transactional
    public String filterByCategory(@RequestParam Long categoryId) {
        final List<Book> books = new ArrayList<>();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        categoryOptional.ifPresent(category -> books.addAll(bookRepository.findAllByCategory(category)));

        final String html = books.stream()
                .map(book -> {
                    final Book copy = Book.create(book);
                    Hibernate.initialize(copy.getAuthors());
                    return copy;
                })
                .map(Book::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));

        return html;
    }

    @GetMapping("/bytitle")
    @ResponseBody
    @Transactional
    public String filterByCatId(@RequestParam final String title) {
        final List<Book> books = bookRepository.findAllByTitle(title);

        final String html = books.stream()
                .map(book -> {
                    final Book copy = Book.create(book);
                    Hibernate.initialize(copy.getAuthors());
                    return copy;
                })
                .map(Book::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));

        return html;
    }
}
