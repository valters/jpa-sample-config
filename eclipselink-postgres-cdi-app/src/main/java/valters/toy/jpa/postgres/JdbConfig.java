package valters.toy.jpa.postgres;

public interface JdbConfig {

    String getServerName();

    String getDatabaseName();

    String getPortNumber();

    String getUsername();

    String getPassword();

}
