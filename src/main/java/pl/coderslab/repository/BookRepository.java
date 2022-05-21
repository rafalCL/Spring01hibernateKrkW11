package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
//    metodę wyszukującą książki dla zadanego tytułu.
    List<Book> findAllByTitle(String title);
//    metodę wyszukującą książki dla zadanej kategorii
    List<Book> findAllByCategory(Category category);
//    metodę wyszukującą książki dla zadanego id kategorii
    List<Book> findAllByCategoryId(long categoryId);
}
