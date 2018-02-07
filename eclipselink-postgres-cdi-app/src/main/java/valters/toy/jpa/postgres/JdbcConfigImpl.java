package valters.toy.jpa.postgres;

import static java.util.Objects.requireNonNull;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import valters.toy.jpa.JdbcConfig;

/**
 * Handles reading sensitive jdbc properties securely.
 * (Main objective is to keep the jdbc production database password a secret.)
 *
 * @author Valters Vingolds
 */
@ApplicationScoped
public class JdbcConfigImpl implements JdbcConfig {

    private static final String CREDENTIALS_PROPERTIES = "jdbc-credentials.properties";

    @PostConstruct
    void loadProperties() throws Exception {

        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CREDENTIALS_PROPERTIES)) {

            requireNonNull(is, "Error, failed to load credentials from [" + CREDENTIALS_PROPERTIES + "] on classpath");

            final Properties p = new Properties();
            p.load(is);
            jdbcUrl = mandatoryProperty(p, "jdbc.url");
            username = mandatoryProperty(p, "jdbc.username");
            password = mandatoryProperty(p, "jdbc.password");
        }
    }

    protected String mandatoryProperty(final Properties p, final String propName) {

        return Optional.ofNullable(p.getProperty(propName)).orElseThrow(
                () -> new RuntimeException("Error, failed to read property [" + propName + "] from credentials config [" + CREDENTIALS_PROPERTIES + "]"));
    }

    private String jdbcUrl = "";

    private String username = "";

    private String password = "";

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }
}
