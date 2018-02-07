package valters.toy.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample Postgres DataSource configuration with CDI, and EclipseLink internal
 * connection pooling. (Unfortunately no HikariCP yet).
 *
 * @author Valters Vingolds
 */
@ApplicationScoped
public class JpaProducer {

    private static final Logger log = LoggerFactory.getLogger(JpaProducer.class);

    @Inject
    private JdbcConfig jdbc;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private EntityManagerFactory entityManagerFactory() {
        final Map properties = new HashMap();

        properties.put("javax.persistence.target-database", "PostgreSQL");
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("javax.persistence.jdbc.url", jdbc.getJdbcUrl());
        properties.put("javax.persistence.jdbc.user", jdbc.getUsername());
        properties.put("javax.persistence.jdbc.password", jdbc.getPassword());

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ValtersTest", properties);
        log.info("factory produced: {}", emf);
        return emf;
    }

    @Produces
    EntityManager entityManager() {

        final EntityManager em = entityManagerFactory().createEntityManager();
        log.info("produced: {}", em);
        return em;
    }

}
