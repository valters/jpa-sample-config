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

    private final String serverName = "(server).rds.amazonaws.com";
    private final String databaseName = "valters_database";
    private final String portNumber = "5432";

    private final String username = "valters_test";

    private final String password = "(password)";

    @Override
    public String getServerName() {
        return serverName;
    }

    @Override
    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public String getPortNumber() {
        return portNumber;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
