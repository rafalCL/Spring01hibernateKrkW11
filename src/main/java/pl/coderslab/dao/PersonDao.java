package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonDao {
    @PersistenceContext
    EntityManager entityManager;
    public void save(Person entity) {
        entityManager.persist(entity);
    }

    public Person findById(long id) {
        return entityManager.find(Person.class, id);
    }

    public void update(Person entity) {
        entityManager.merge(entity);
    }

    public void delete(Person entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<Person> findAll() {
        return entityManager
                .createQuery("SELECT e FROM Person e")
                .getResultList();
    }
}
