package valters.toy.postgres.connection.jpa;

import org.springframework.stereotype.Component;

/**
 * Handles reading sensitive jdbc properties securely.
 * (Main objective is to keep the jdbc production database password a secret.)
 *
 * @author Valters Vingolds
 */
@Component
public class JdbcConfig {

    public final String serverName = "(server).rds.amazonaws.com";
    public final String databaseName = "valters_database";
    public final String portNumber = "5432";

    public final String username = "valters_test";

    public final String password = "(password)";
}
