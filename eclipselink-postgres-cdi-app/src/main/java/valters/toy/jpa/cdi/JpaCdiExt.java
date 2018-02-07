package valters.toy.jpa.cdi;

import java.util.Set;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

import valters.toy.jpa.postgres.AfterInit;

/**
 * Performs Eager initialization of {@link AfterInit} bean.
 */
public class JpaCdiExt implements Extension {

    /** Hooks on event just after CDI has bootstrapped. */
    void afterDeploymentValidation(@Observes final AfterDeploymentValidation adb, final BeanManager beanManager) {

        final Set<Bean<?>> l = beanManager.getBeans("afterInit");
        System.out.println("****** afterDeployment: " + l);
        final Bean<?> bean = l.iterator().next();
        final Object obj = beanManager.getReference(bean, bean.getBeanClass(), beanManager.createCreationalContext(bean));
        System.out.println("****** init: " + obj.toString());
    }
}
