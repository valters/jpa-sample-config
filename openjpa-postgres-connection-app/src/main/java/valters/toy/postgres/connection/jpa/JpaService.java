package valters.toy.postgres.connection.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import valters.toy.jpa.entity.Sample;

@Service
public class JpaService implements JpaShowcase {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Sample loadSample(final long id) {

        final Query query = entityManager.createQuery("SELECT e FROM Sample e WHERE e.id = :id");
        query.setParameter("id", id);
        final Sample s = (Sample)query.getSingleResult();

        return s;
    }

    @Override
    @Transactional
    public Sample save(final Sample obj) {
        return entityManager.merge(obj);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateItem() {
        final Query query = entityManager.createNativeQuery("update test_table set info='what do you know' where id = 1");

        return query.executeUpdate();

    }

}
