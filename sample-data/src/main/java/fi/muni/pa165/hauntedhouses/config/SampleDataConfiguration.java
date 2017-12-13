package fi.muni.pa165.hauntedhouses.config;

import fi.muni.pa165.hauntedhouses.data.Initializer;
import fi.muni.pa165.hauntedhouses.data.InitializerImpl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Klara Kufova, 410091
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {InitializerImpl.class})
public class SampleDataConfiguration {

    @Inject
    private Initializer initializer;

    @PostConstruct
    public void dataLoading() {
        initializer.loadData();
    }
    
}
