package valters.toy.jpa.postgres;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import valters.toy.jpa.entity.Sample;

@ApplicationScoped
public class JpaService implements JpaShowcase {

    @Inject
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

        entityManager.getTransaction().begin();

        final Sample rv = entityManager.merge(obj);

        entityManager.getTransaction().commit();

        return rv;
    }

    @Override
    public Sample create(final Sample obj) {

        entityManager.getTransaction().begin();

        entityManager.persist(obj);

        entityManager.getTransaction().commit();

        return obj;
    }

    @Override
    public int updateItem() {

        entityManager.getTransaction().begin();

        final Query query = entityManager.createNativeQuery("update test_table set info='what do you know' where id = 1");

        final int rv = query.executeUpdate();

        entityManager.getTransaction().commit();

        return rv;

    }

}
