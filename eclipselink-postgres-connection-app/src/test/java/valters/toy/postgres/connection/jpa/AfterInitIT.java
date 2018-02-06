package valters.toy.postgres.connection.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("classpath:META-INF/spring/jpa-beans.xml")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AfterInitIT {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @PersistenceContext
    private EntityManager em;

    @Test
    public void shouldGreet() {
        System.out.println("hello from junit");
    }


}
