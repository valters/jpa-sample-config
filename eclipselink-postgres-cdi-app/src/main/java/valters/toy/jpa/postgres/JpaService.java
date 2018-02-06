package valters.toy.jpa.postgres;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import valters.toy.jpa.entity.Sample;

@ApplicationScoped
public class JpaService implements JpaShowcase {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Sample loadSample(final long id) {

        final Query query = entityManager.createQuery("SELECT e FROM Sample e WHERE e.id = :id");
        query.setParameter("id", id);
        final Sample s = (Sample)query.getSingleResult();

        return s;
    }

    @Override
    public Sample save(final Sample obj) {
        return entityManager.merge(obj);
    }

    @Override
    public int updateItem() {
        final Query query = entityManager.createNativeQuery("update test_table set info='what do you know' where id = 1");

        return query.executeUpdate();

    }

}
