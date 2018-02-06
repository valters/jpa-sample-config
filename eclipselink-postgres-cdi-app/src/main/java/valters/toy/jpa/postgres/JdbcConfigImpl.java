package valters.toy.jpa.postgres;

import javax.enterprise.context.ApplicationScoped;

/**
 * Handles reading sensitive jdbc properties securely.
 * (Main objective is to keep the jdbc production database password a secret.)
 *
 * @author Valters Vingolds
 */
@ApplicationScoped
public class JdbcConfigImpl implements JdbConfig {

    private final String jdbcUrl = "jdbc:postgresql://(server).rds.amazonaws.com:5432/(db-name)";

    private final String username = "valters_test";

    private final String password = "(password)";


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
