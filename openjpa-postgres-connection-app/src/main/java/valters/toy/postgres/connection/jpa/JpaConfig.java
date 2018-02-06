package valters.toy.postgres.connection.jpa;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Sample Postgres DataSource configuration with HikariCP and Spring.
 *
 * @author Valters Vingolds
 */
@Configuration
@EnableTransactionManagement
@Import(JdbcConfig.class)
public class JpaConfig {

    @Autowired
    private JdbcConfig jdbc;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new OpenJpaVendorAdapter());
        emf.setPersistenceXmlLocation("classpath:META-INF/persistence-postgres.xml");
        emf.setPersistenceUnitName("ValtersTest");

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean(destroyMethod = "close")
    DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();

        ds.setMaximumPoolSize(5); // TODO: change this for production

        ds.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        ds.addDataSourceProperty("serverName", jdbc.serverName);
        ds.addDataSourceProperty("databaseName", jdbc.databaseName);
        System.out.println("jdbc:" + jdbc.serverName + "/" + jdbc.databaseName);
        ds.addDataSourceProperty("user", jdbc.username);
        ds.addDataSourceProperty("password", jdbc.password);

        ds.addDataSourceProperty("prepareThreshold", "1");
        ds.addDataSourceProperty("assumeMinServerVersion", "9.6");

        return ds;
    }

}
