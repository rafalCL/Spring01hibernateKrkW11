package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Author entity) {
        entityManager.persist(entity);
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void update(Author entity) {
        entityManager.merge(entity);
    }

    public void delete(Author entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<Author> findAll() {
        return entityManager
                .createQuery("SELECT e FROM Author e")
                .getResultList();
    }
}
