package valters.toy.jpa.postgres;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import valters.toy.jpa.JpaProducer;
import valters.toy.jpa.entity.Sample;
import valters.toy.jpa.entity.Sample_;

@RunWith(Arquillian.class)
public class QueriesIT {

    private static final Logger log = LoggerFactory.getLogger(QueriesIT.class);

    @Deployment
    public static Archive<?> deployment() {
        final WebArchive arch = ShrinkWrap.create(WebArchive.class, QueriesIT.class.getSimpleName() + ".war")
                .addClass(JpaProducer.class)
                .addClass(JdbcConfigImpl.class)
                .addClass(JpaService.class)
                .addClass(AfterInit.class)
                .addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/beans.xml")), "beans.xml")
                .addAsWebInfResource("jetty.xml")
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));

        log.info("Running with archive: {}", arch.toString(true));
        return arch;
    }

    @Inject
    private EntityManager em;

    @Inject
    private JpaShowcase test;

    @Test
    public void shouldUpdate() {
        assertThat(test.updateItem(), is(1));
    }

    @Test
    public void shouldModify() {
        final Sample s = test.loadSample(2);
        log.info("loaded entry: {}", s);

        s.setInfo("hello to you too, not too shabby actually!");
        final Sample s2 = test.save(s);
        assertThat(s2, notNullValue());
        assertThat(s2.getInfo(), startsWith("hello to"));

    }

    @Test
    public void shouldCreate() {

        final long next = em.createQuery(findMaxId()).getSingleResult() + 1;
        log.info("next entry #: {}", next);

        final Sample s = new Sample();
        s.setId(next);
        s.setInfo("can commit");

        test.create(s);

        final Sample s3 = test.loadSample(next);
        log.info("loaded entry: {}", s3);
        assertThat(s3, notNullValue());
        assertThat(s3.getInfo(), startsWith("can "));
    }

    protected CriteriaQuery<Long> findMaxId() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> findMax = cb.createQuery(Long.class);
        final CriteriaQuery<Sample> q = cb.createQuery(Sample.class);
        final Root<Sample> root = q.from(Sample.class);
        findMax.select(cb.max(root.get(Sample_.id)));
        return findMax;
    }

}
