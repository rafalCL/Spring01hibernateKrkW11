package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

// currently not used. using spring built in id->entity mapper (path="publisher.id")
public class PublisherConverter implements Converter<String, Publisher> {
    @Autowired
    private PublisherDao dao;

    @Override
    public Publisher convert(String id) {
        return dao.findById(Long.parseLong(id));
    }
}
