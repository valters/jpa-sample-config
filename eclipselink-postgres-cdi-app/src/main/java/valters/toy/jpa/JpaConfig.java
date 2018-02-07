package valters.toy.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import valters.toy.jpa.postgres.JdbcConfig;

/**
 * Sample Postgres DataSource configuration with CDI, and EclipseLink internal
 * connection pooling. (Unfortunately no HikariCP yet).
 *
 * @author Valters Vingolds
 */
@ApplicationScoped
public class JpaConfig {

    @Inject
    private JdbcConfig jdbc;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Produces
    EntityManager entityManager() {

        final Map properties = new HashMap();

        properties.put("javax.persistence.target-database","PostgreSQL");
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("javax.persistence.jdbc.url", jdbc.getJdbcUrl());
        properties.put("javax.persistence.jdbc.user", jdbc.getUsername());
        properties.put("javax.persistence.jdbc.password", jdbc.getPassword());

        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("ValtersTest", properties);

        final EntityManager em = factory.createEntityManager();
        System.out.println("produced: " + em);
        return em;
    }

}
