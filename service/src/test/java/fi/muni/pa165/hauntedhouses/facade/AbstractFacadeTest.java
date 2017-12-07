package fi.muni.pa165.hauntedhouses.facade;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fi.muni.pa165.hauntedhouses.config.ServiceConfiguration;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;

@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractFacadeTest {

    @Mock
    @Inject
    protected BeanMappingService beanMappingService;

    public static final Object unwrapProxy(Object bean) throws Exception {
        // If the given object is a proxy, set the return value as the object
        // being proxied, otherwise return the given object.
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Advised advised = (Advised) bean;
            bean = advised.getTargetSource().getTarget();
        }
        return bean;
    }
    
}
