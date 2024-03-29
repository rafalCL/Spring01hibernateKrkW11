package pl.coderslab.controller;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.BookRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookFormController {
    private final PublisherDao publisherDao;
    private final BookRepository bookRepository;
    private final AuthorDao authorDao;

    public BookFormController(PublisherDao publisherDao, BookRepository bookRepository, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookRepository = bookRepository;
        this.authorDao = authorDao;
    }

    @GetMapping("/form")
    public String getForm(Model m) {
        m.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/form")
    public String getForm(@Valid final Book book, final BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "books/form";
        }
        bookRepository.save(book);
        return "redirect:list";
    }

    @GetMapping("/list")
    @ResponseBody
    @Transactional
    public String getList() {
        return bookRepository.findAll()
                .stream()
                .map(book -> {
                    final Book copy = Book.create(book);
                    Hibernate.initialize(copy.getAuthors());
                    return copy;
                })
                .map(Book::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        List<Publisher> publisherList = publisherDao.findAll();
        return publisherList;
    }

    @ModelAttribute("authors")
    public List<Author> authors() {
        List<Author> entities = authorDao.findAll();
        return entities;
    }
}
