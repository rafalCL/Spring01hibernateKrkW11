package pl.coderslab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.converter.AuthorConverter;
import pl.coderslab.converter.PublisherConverter;

@Configuration
@ComponentScan("pl.coderslab")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(publisherConverter());
        registry.addConverter(authorConverter());
    }

//    @Bean
//    public Converter publisherConverter() {
//        return new PublisherConverter();
//    }

    @Bean
    public Converter authorConverter() {
        return new AuthorConverter();
    }
}
