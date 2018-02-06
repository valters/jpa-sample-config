package valters.toy.jpa.postgres;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@ApplicationScoped
@Named("afterInit")
public class AfterInit {

    @Inject
    private EntityManager entityManager;

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
    public String toString() {
        return "AfterInit [entityManager=" + entityManager + "]";
    }

}
