package valters.toy.mysql.connection.jpa;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

@ContextConfiguration("classpath:META-INF/spring/jpa-beans.xml")
public class AfterInitIT {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Test
    public void shouldSayHello() {
        System.out.println("hello from junit");
    }

}
