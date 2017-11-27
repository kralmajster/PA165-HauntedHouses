package fi.muni.pa165.hauntedhouses.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.service.PersonService;
/**
 * 
 * @author Mario Majernik, 422165
 *
 */
public class PersonFacadeTest extends AbstractFacadeTest{

    @Mock
    private PersonService personService;

    @Autowired
    @InjectMocks
    private PersonFacadeImpl personFacade;

    private Person person;

    private PersonDTO personDTO;

    private Long personId = 1L;
    private String personName = "Matej";
    
    private List<Person> persons;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        personFacade = (PersonFacadeImpl) unwrapProxy(personFacade);
        ReflectionTestUtils.setField(personFacade, "personService", personService);
        ReflectionTestUtils.setField(personFacade, "beanMappingService", beanMappingService);

        person = new Person();
        person.setName(personName);

        personDTO = new PersonDTO();
        personDTO.setName(personName);
    }

    @Test
    public void testCreatePerson() {
    	when(beanMappingService.mapTo(personDTO, Person.class)).thenReturn(person);
        personFacade.registerPerson(personDTO);

        verify(personService).registerPerson(person, "pass");
        verify(beanMappingService).mapTo(personDTO, Person.class);
    }

    @Test
    public void testUpdatePerson() {
    	when(beanMappingService.mapTo(personDTO, Person.class)).thenReturn(person);
        personFacade.registerPerson(personDTO);

        verify(personService).registerPerson(person, "pass");
        verify(beanMappingService).mapTo(personDTO, Person.class);

        personDTO.setSurname("Maly");

        personFacade.updatePerson(personDTO);
        verify(personService).updatePerson(person);
        verify(beanMappingService).mapTo(personDTO, Person.class);
    }

    @Test
    public void testDeletePerson() {
    	when(beanMappingService.mapTo(personDTO, Person.class)).thenReturn(person);
        personFacade.registerPerson(personDTO);

        verify(personService).registerPerson(person, "pass");
        verify(beanMappingService).mapTo(personDTO, Person.class);
        
        personFacade.removePerson(personId);
        verify(personService).removePerson(person);
        verify(beanMappingService.mapTo(personDTO, Person.class));
    }

    @Test
    public void testFindAll() {
        when(personService.getAllPeople()).thenReturn(Collections.singletonList(person));

        Collection<PersonDTO> persons = personFacade.getAllPeople();
        List<PersonDTO> per = (List<PersonDTO>) persons;

        assertThat(person.getName()).isEqualTo(per.get(0).getName());
        verify(personService).getAllPeople();
        verify(beanMappingService).mapTo(Collections.singletonList(person), PersonDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(personService.getAllPeople()).thenReturn(null);

        Collection<PersonDTO> persons = personFacade.getAllPeople();

        assertThat(persons).isNull();
        verify(personService).getAllPeople();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(personService.findPersonById(personId)).thenReturn(person);

        PersonDTO personDTO = personFacade.findPersonById(personId);

        assertThat(personDTO).isNotNull();
        assertThat(person.getName()).isEqualTo(personDTO.getName());
        verify(personService).findPersonById(personId);
        verify(beanMappingService).mapTo(person, PersonDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(personService.findPersonById(personId)).thenReturn(null);

        PersonDTO personDTO = personFacade.findPersonById(personId);

        assertThat(personDTO).isNull();
        verify(personService).findPersonById(personId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(personService.findPersonByName(personName)).thenReturn(persons);

        List<PersonDTO> personDTO = personFacade.findPersonByName(personName);
        
        assertThat(personDTO).isNotNull();
        assertThat(person.getName()).isEqualTo(personDTO.get(0).getName());
        verify(personService).findPersonByName(personName);
        verify(beanMappingService).mapTo(person, PersonDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(personService.findPersonByName(personName)).thenReturn(null);

        List<PersonDTO> personDTO = personFacade.findPersonByName(personName);

        assertThat(personDTO).isNull();
        verify(personService).findPersonByName(personName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }
}
