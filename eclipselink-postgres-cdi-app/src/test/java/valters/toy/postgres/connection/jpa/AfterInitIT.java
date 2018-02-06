package valters.toy.postgres.connection.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;

public class AfterInitIT {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void shouldGreet() {
        System.out.println("hello from junit");
    }


}
