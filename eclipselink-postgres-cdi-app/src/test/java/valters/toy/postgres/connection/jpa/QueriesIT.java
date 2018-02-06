package valters.toy.postgres.connection.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;

import valters.toy.jpa.entity.Sample;
import valters.toy.jpa.postgres.JpaShowcase;

public class QueriesIT {

    @Inject
    private JpaShowcase test;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void shouldUpdate() {
        assertThat(test.updateItem(), is(1));
    }

    @Test
    public void shouldModify() {
        final Sample s = test.loadSample(2);
        System.out.println("loaded entry: " + s);

        s.setInfo("hello to you too, not too shabby actually!");
        final Sample s2 = test.save(s);
        assertThat(s2, notNullValue());
        assertThat(s2.getInfo(), startsWith("hello to"));

    }

    @Test
    public void shouldCreate() {

        final Sample s = new Sample();
        s.setId(3L);
        s.setInfo("can commit");

        em.persist(s);

        final Sample s3 = test.loadSample(3);
        System.out.println("loaded entry: " + s3);
        assertThat(s3, notNullValue());
        assertThat(s3.getInfo(), startsWith("can "));
    }

}
