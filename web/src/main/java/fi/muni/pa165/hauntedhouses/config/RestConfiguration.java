package fi.muni.pa165.hauntedhouses.config;

import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Locale;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import fi.muni.pa165.hauntedhouses.controllers.AbilityController;
import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.mixin.AbilityDTOMixin;
import fi.muni.pa165.hauntedhouses.mixin.GhostDTOMixin;
import fi.muni.pa165.hauntedhouses.mixin.HouseDTOMixin;
import fi.muni.pa165.hauntedhouses.mixin.PersonDTOMixin;

/**
 *
 * @author Mario Majernik (422165)
 * @author Klara Kufova (410091)
 *
 */

@Configuration
@EnableWebMvc
@Import({ServiceConfiguration.class, SampleDataConfiguration.class})
@ComponentScan(basePackageClasses = {AbilityController.class})
public class RestConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }

    private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));

        objectMapper.addMixIn(AbilityDTO.class, AbilityDTOMixin.class);
        objectMapper.addMixIn(GhostDTO.class, GhostDTOMixin.class);
        objectMapper.addMixIn(HouseDTO.class, HouseDTOMixin.class);
        objectMapper.addMixIn(PersonDTO.class, PersonDTOMixin.class);

        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
