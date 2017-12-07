package fi.muni.pa165.hauntedhouses.service;

import javax.inject.Inject;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import fi.muni.pa165.hauntedhouses.config.ServiceConfiguration;

/**
 *
 * @author Mario Majernik, 422165
 *
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public abstract class AbstractServiceTest extends AbstractTestNGSpringContextTests {

    @Inject
    protected BeanMappingService beanMappingService;
    
}
