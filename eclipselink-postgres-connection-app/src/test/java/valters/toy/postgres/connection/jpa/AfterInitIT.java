package valters.toy.postgres.connection.jpa;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import valters.toy.postgres.entity.Sample;

@ContextConfiguration("classpath:META-INF/spring/jpa-beans.xml")
public class AfterInitIT {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private AfterInit test;

    @Test
    public void shouldSayHello() {
        System.out.println("hello from junit");

        test.checkTables();

        final Sample s = test.loadSample(2);
        System.out.println("loaded entry: " + s);

        s.setInfo("hello to you too, not too shabby actually!");
        test.save(s);

        test.flush();
    }

}
