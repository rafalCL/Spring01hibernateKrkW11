package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookFormController {
    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public BookFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/form")
    public String getForm(Model m) {
        m.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/form")
    public String getForm(Book book) {
        bookDao.saveBook(book);
        return "redirect:list";
    }

    @GetMapping("/list")
    @ResponseBody
    public String getList() {
        return bookDao.findAll()
                .stream()
                .map(Book::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers(){
        List<Publisher> publisherList = publisherDao.findAll();
        return publisherList;
    }

    @ModelAttribute("authors")
    public List<Author> authors(){
        List<Author> entities = authorDao.findAll();
        return entities;
    }
}
