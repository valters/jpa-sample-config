package valters.toy.postgres.connection.jpa;

import valters.toy.postgres.entity.Sample;

public interface JpaShowcase {

    void checkTables();

    Sample loadSample(long id);

    void save(Sample obj);

    void updateItem();

}
