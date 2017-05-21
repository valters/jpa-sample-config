package valters.toy.mysql.connection.jpa;

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
        System.out.println( "************* checking tables!" );

        Query query = entityManager.createNativeQuery( "select * from test_table" );

        @SuppressWarnings("unchecked")
        List<String> res = query.getResultList();

        System.out.println( "got results: " + res.size() );
        System.out.println( " . returned: [" + res.get( 0 ) + "]" );
    }

}
