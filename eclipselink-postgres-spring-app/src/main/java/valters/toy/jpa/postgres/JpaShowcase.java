package valters.toy.jpa.postgres;

import valters.toy.jpa.entity.Sample;

public interface JpaShowcase {

    Sample loadSample(long id);

    Sample save(Sample obj);

    int updateItem();

}
