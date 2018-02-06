package valters.toy.postgres.connection.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

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

}
