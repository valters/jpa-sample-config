package valters.toy.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import valters.toy.jpa.postgres.JdbConfig;

/**
 * Sample Postgres DataSource configuration with HikariCP and Spring.
 *
 * @author Valters Vingolds
 */
@ApplicationScoped
public class JpaConfig {

    @Inject
    private JdbConfig jdbc;

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

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setJpaDialect(dialect());
//        emf.setJpaVendorAdapter(eclipseLink());
//        emf.setPersistenceXmlLocation("classpath:META-INF/persistence-postgres.xml");
//        emf.setPersistenceUnitName("ValtersTest");
//
//        return emf;
//    }
//
//    private JpaDialect dialect() {
//        final EclipseLinkJpaDialect dialect = new EclipseLinkJpaDialect();
//        dialect.setLazyDatabaseTransaction(true); // required by Spring to use shared cache
//        return dialect;
//    }
//
//    private JpaVendorAdapter eclipseLink() {
//        final EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
//        adapter.setDatabasePlatform("org.eclipse.persistence.platform.database.PostgreSQLPlatform");
//        return adapter;
//    }
//

}
