package valters.toy.postgres.connection.jpa;

import valters.toy.jpa.entity.Sample;

public interface JpaShowcase {

    Sample loadSample(long id);

    Sample save(Sample obj);

    int updateItem();

}
