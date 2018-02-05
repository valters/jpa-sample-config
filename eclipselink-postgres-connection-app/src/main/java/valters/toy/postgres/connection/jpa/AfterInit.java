package valters.toy.postgres.connection.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import valters.toy.postgres.entity.Sample;

@Component
public class AfterInit {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void checkTables() {
        System.out.println("************* checking tables!");

        final Query query = entityManager.createNativeQuery("select * from test_table");

        @SuppressWarnings("unchecked")
        final List<String> res = query.getResultList();

        System.out.println("got results: " + res.size());
        System.out.println(" . returned: [" + String.valueOf(res.get(0)) + "]");
    }

    public Sample loadSample(final long id) {

        final Query query = entityManager.createQuery("SELECT e FROM Sample e WHERE e.id = :id");
        query.setParameter("id", id);
        final Sample s = (Sample)query.getSingleResult();

        return s;
    }

    @Transactional
    public void save(final Sample s) {
        entityManager.persist(s);
    }

    public void flush() {
        entityManager.flush();
    }

}
