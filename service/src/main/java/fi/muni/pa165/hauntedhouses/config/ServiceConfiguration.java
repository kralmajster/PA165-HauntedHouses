
package fi.muni.pa165.hauntedhouses.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fi.muni.pa165.hauntedhouses.PersistenceApplicationContext;
import fi.muni.pa165.hauntedhouses.facade.PersonFacadeImpl;
import fi.muni.pa165.hauntedhouses.service.PersonServiceImpl;

/**
 *
 * @author Adam Dobias
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {PersonServiceImpl.class, PersonFacadeImpl.class})
public class ServiceConfiguration {
    
    @Bean
    public Mapper dozer(){
            DozerBeanMapper dozer = new DozerBeanMapper();		
            return dozer;
    }

}