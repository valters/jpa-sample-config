package valters.toy.postgres.connection.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import valters.toy.jpa.JpaConfig;
import valters.toy.jpa.entity.Sample;
import valters.toy.jpa.postgres.JpaShowcase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JpaConfig.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QueriesIT {

    //    @ClassRule
    //    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    //
    //    @Rule
    //    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private JpaShowcase test;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Commit
    @Test
    public void shouldUpdate() {
        assertThat(test.updateItem(), is(1));
    }

    @Commit
    @Test
    public void shouldModify() {
        final Sample s = test.loadSample(2);
        System.out.println("loaded entry: " + s);

        s.setInfo("hello to you too, not too shabby actually!");
        final Sample s2 = test.save(s);
        assertThat(s2, notNullValue());
        assertThat(s2.getInfo(), startsWith("hello to"));

    }

    @Rollback
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
