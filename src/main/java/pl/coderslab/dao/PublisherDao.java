package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    EntityManager entityManager;

    public void save(Publisher entity) {
        entityManager.persist(entity);
    }

    public Publisher findById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void update(Publisher entity) {
        entityManager.merge(entity);
    }

    public void delete(Publisher entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<Publisher> findAll() {
        List publishers = entityManager
                .createQuery("SELECT e FROM Publisher e")
                .getResultList();
        return publishers;
    }
}
