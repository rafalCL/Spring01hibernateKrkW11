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

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
    }

    // example request GET http://localhost:8080/hiber/book/add?title=Thinking+in+Java&rating=10&description=best+book&publisherId=1
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
        return "id="+book.getId();
    }
}
