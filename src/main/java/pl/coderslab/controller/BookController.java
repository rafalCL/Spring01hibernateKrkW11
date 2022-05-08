package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
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

        bookDao.saveBook(book);
        return "id=" + book.getId();
    }

    @GetMapping("")
    @ResponseBody
    public String filter(@RequestParam(required = false) Integer minRating) {
        final List<Book> books = new ArrayList<>();
        if (minRating == null) {
            books.addAll(bookDao.findAll());
        } else {
            books.addAll(bookDao.findByRating(minRating));
        }
        final String html = books.stream()
                .map(Book::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));

        return html;
    }
}
