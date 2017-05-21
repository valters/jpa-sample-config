package valters.toy.mysql.connection.jpa;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Sample MySQL DataSource configuration with HikariCP and Spring.
 *
 * @author Valters Vingolds
 */
@Configuration
@EnableTransactionManagement
public class JpaConfig {

    private String url = "jdbc:mysql://127.0.0.1:3306/valters-test?autoReconnect=true";
	private String username = "valters";
	private String password = "testing";

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceXmlLocation("classpath:META-INF/persistence-mysql.xml");
        emf.setPersistenceUnitName("ValtersTest");
//        sessionFactory.setHibernateProperties(hibernateProperties());
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
    	JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getNativeEntityManagerFactory());

        return txManager;
    }

    @Bean(destroyMethod = "close")
    DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(100);
        ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        ds.addDataSourceProperty("url", url);
        ds.addDataSourceProperty("user", username);
        ds.addDataSourceProperty("password", password);

        ds.addDataSourceProperty("cachePrepStmts", true);
        ds.addDataSourceProperty("prepStmtCacheSize", 250);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        ds.addDataSourceProperty("useServerPrepStmts", true);

        ds.addDataSourceProperty("dumpQueriesOnException", true);
        ds.addDataSourceProperty("logSlowQueries", true);
        return ds;
    }

    private Properties hibernateProperties() {
        final Properties properties = new Properties();
//        ... (Dialect, 2nd level entity cache, query cache, etc.)
        properties.setProperty( "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect" );
        return properties;
    }

}
