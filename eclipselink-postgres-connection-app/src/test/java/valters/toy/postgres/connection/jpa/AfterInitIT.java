package valters.toy.postgres.connection.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;

import valters.toy.postgres.entity.Sample;

@ContextConfiguration("classpath:META-INF/spring/jpa-beans.xml")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AfterInitIT {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private JpaShowcase test;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void shouldGreet() {
        System.out.println("hello from junit");

        test.checkTables();

    }

    @Rollback(false)
    @Test
    public void shouldModify() {
        final Sample s = test.loadSample(2);
        System.out.println("loaded entry: " + s);

        s.setInfo("hello to you too, not too shabby actually!");
        test.save(s);
    }

    @Commit
    @Test
    public void shouldCreate() {

        final Sample s = new Sample();
        s.setId(3L);
        s.setInfo("can commit");

        em.persist(s);

        final Sample s3 = test.loadSample(3);
        System.out.println("loaded entry: " + s3);
    }

//    @Commit
    @Test
    public void shouldUpdate() {
        test.updateItem();
    }

}
