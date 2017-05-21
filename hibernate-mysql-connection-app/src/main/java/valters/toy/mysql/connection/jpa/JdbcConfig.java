package valters.toy.mysql.connection.jpa;

import org.springframework.stereotype.Component;

/**
 * Handles reading sensitive jdbc properties securely.
 * (Main objective is to keep the jdbc production database password a secret.)
 *
 * @author Valters Vingolds
 */
@Component
public class JdbcConfig {

    public final String url = "jdbc:mysql://127.0.0.1:3306/valters-test?autoReconnect=true";

    public final String username = "valters";

    public final String password = "testing";
}
