package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.entity.Person;

@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/form")
    public String getForm() {
        return "person/form";
    }

    @PostMapping("/form")
    @ResponseBody
    public String postForm(
            @RequestParam final String login,
            @RequestParam final String password,
            @RequestParam final String email
    ) {
        Person person = new Person(login, password, email);
        personDao.save(person);
        return "id="+person.getId();
    }
}
