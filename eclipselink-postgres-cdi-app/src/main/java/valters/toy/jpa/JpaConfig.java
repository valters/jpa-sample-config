package valters.toy.jpa;

import javax.inject.Inject;

import valters.toy.jpa.postgres.JdbConfig;

/**
 * Sample Postgres DataSource configuration with HikariCP and Spring.
 *
 * @author Valters Vingolds
 */
public class JpaConfig {

    @Inject
    private JdbConfig jdbc;

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
//    @Bean
//    public JpaTransactionManager transactionManager(final EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//
//    @Bean(destroyMethod = "close")
//    DataSource dataSource() {
//        final HikariDataSource ds = new HikariDataSource();
//
//        ds.setMaximumPoolSize(5); // TODO: change this for production
//
//        ds.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//        ds.addDataSourceProperty("serverName", jdbc.serverName);
//        ds.addDataSourceProperty("databaseName", jdbc.databaseName);
//        System.out.println("jdbc:" + jdbc.serverName + "/" + jdbc.databaseName);
//        ds.addDataSourceProperty("user", jdbc.username);
//        ds.addDataSourceProperty("password", jdbc.password);
//
//        ds.addDataSourceProperty("prepareThreshold", "1");
//        ds.addDataSourceProperty("assumeMinServerVersion", "9.6");
//
//        return ds;
//    }

}
