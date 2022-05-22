package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
//    metodę wyszukującą książki dla zadanego tytułu.
    List<Book> findAllByTitle(String title);
//    metodę wyszukującą książki dla zadanej kategorii
    List<Book> findAllByCategory(Category category);
//    metodę wyszukującą książki dla zadanego id kategorii
    List<Book> findAllByCategoryId(long categoryId);

    List<Book> findByRatingGreaterThanEqual(Integer minRating);

    // metodę wyszukującą książki dla zadanego tytułu.
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    List<Book> findByTitle(@Param("title") String title);
    //metodę wyszukującą książki dla zadanej kategorii
    @Query("SELECT b FROM Book b WHERE b.category = :category")
    List<Book> findByCategory(@Param("category") Category category);
}
