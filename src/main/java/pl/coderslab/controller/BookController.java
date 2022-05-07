package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/add")
    @ResponseBody
    public String addBook(@RequestParam final String title,
                          @RequestParam final Integer rating,
                          @RequestParam final String description) {
        Book book = new Book(title, rating, description, null);

        bookDao.saveBook(book);
        return "id="+book.getId();
    }
}
