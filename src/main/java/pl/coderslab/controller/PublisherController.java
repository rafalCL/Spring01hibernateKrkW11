package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.PublisherRepository;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/add")
    @ResponseBody
    public String addBook(@RequestParam final String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.save(publisher);
        return "id=" + publisher.getId();
    }

    @GetMapping("/list")
    @ResponseBody
    public String getList() {
        return publisherRepository.findAll()
                .stream()
                .map(Publisher::toString)
                .collect(Collectors.joining("</div><div>", "<div>", "</div>"));
    }
}
