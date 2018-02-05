package valters.toy.postgres.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_table")
public class Sample {

    @Id
    private Long id;

    private String info;

    @Override
    public String toString() {
        return "Sample [id=" + id + ", info=" + info + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

}
