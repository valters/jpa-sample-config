package valters.toy.postgres.connection.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import valters.toy.postgres.entity.Sample;

@Component
public class AfterInit implements JpaShowcase {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @PostConstruct
    public void checkTables() {
        System.out.println("************* checking tables!");

        final Query query = entityManager.createNativeQuery("select * from test_table");

        @SuppressWarnings("unchecked")
        final List<Object> res = query.getResultList();

        System.out.println("got results: " + res.size());
        System.out.println(" . returned: [" + String.valueOf(res.get(0)) + "]");
    }

    @Override
    public Sample loadSample(final long id) {

        final Query query = entityManager.createQuery("SELECT e FROM Sample e WHERE e.id = :id");
        query.setParameter("id", id);
        final Sample s = (Sample)query.getSingleResult();

        return s;
    }

    @Override
    @Transactional
    public void save(final Sample obj) {
        entityManager.merge(obj);
    }

    @Override
//    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void updateItem() {
        final Query query = entityManager.createNativeQuery("update from test_table set info='what do you know' where id = 1");

        query.executeUpdate();

    }

}
