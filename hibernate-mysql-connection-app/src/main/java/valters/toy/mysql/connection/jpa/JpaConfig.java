package valters.toy.mysql.connection.jpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
@Import(JdbcConfig.class)
public class JpaConfig {

    @Autowired
    private JdbcConfig jdbc;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource( dataSource() );
        emf.setPersistenceXmlLocation( "classpath:META-INF/persistence-mysql.xml" );
        emf.setPersistenceUnitName( "ValtersTest" );

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory( entityManagerFactory().getNativeEntityManagerFactory() );

        return txManager;
    }

    @Bean(destroyMethod = "close")
    DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize( 100 );
        ds.setDataSourceClassName( "com.mysql.jdbc.jdbc2.optional.MysqlDataSource" );
        ds.addDataSourceProperty( "url", jdbc.url );
        System.out.println( "jdbc:" + jdbc.url );
        ds.addDataSourceProperty( "user", jdbc.username );
        ds.addDataSourceProperty( "password", jdbc.password );

        ds.addDataSourceProperty( "cachePrepStmts", true );
        ds.addDataSourceProperty( "prepStmtCacheSize", 250 );
        ds.addDataSourceProperty( "prepStmtCacheSqlLimit", 2048 );
        ds.addDataSourceProperty( "useServerPrepStmts", true );

        ds.addDataSourceProperty( "dumpQueriesOnException", true );
        ds.addDataSourceProperty( "logSlowQueries", true );

        return ds;
    }

}
