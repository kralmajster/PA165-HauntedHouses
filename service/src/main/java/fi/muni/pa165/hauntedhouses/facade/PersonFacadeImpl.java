package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.Role;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;
import fi.muni.pa165.hauntedhouses.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.Collection;
import java.util.List;

/**
 * @author Marek Bohm, 396257
 */

@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

    private static final Logger log = LoggerFactory.getLogger(PersonFacadeImpl.class);

    @Inject
    private PersonService personService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public PersonDTO registerPerson(PersonDTO personDTO) {
        log.debug("Creating the person {}", personDTO);
        Person person = beanMappingService.mapTo(personDTO, Person.class);
        personService.registerPerson(person, personDTO.getPassword());
        personDTO.setId(person.getId());
        return personDTO;
    }

    @Override
    public void removePerson(Long id) {
        log.debug("Removing the person with the ID {}", id);
        Person person = beanMappingService.mapTo(findPersonById(id), Person.class);
        personService.removePerson(person);
    }

    @Override
    public void updatePerson(PersonDTO personDTO) {
        log.debug("Updating the person {}", personDTO);
        Person person = beanMappingService.mapTo(personDTO, Person.class);
        personService.updatePerson(person);
    }

    @Override
    public Collection<PersonDTO> getAllPeople() {
        log.debug("Fetching all people");
        List<Person> people = personService.getAllPeople();
        return people == null ? null : beanMappingService.mapTo(people, PersonDTO.class);
    }

    @Override
    public boolean isAllowed(PersonDTO personDTO, List<Role> accessConstraint) {
        log.debug("Checking whether the person {} has the required rights", personDTO);
        Person person = beanMappingService.mapTo(personDTO, Person.class);
        return personService.isAllowed(person, accessConstraint);
    }

    @Override
    public PersonDTO findPersonById(Long id) {
        log.debug("Finding a person by the ID {}", id);
        return beanMappingService.mapTo(personService.findPersonById(id), PersonDTO.class);
    }

    @Override
    public List<PersonDTO> findPersonByName(String name) {
        log.debug("Finding a person by the name {}", name);
        return beanMappingService.mapTo(personService.findPersonByName(name), PersonDTO.class);
    }

    @Override
    public void inhabitHouse(HouseDTO houseDTO, PersonDTO personDTO) {
        log.debug("Moving the person {} to the house {}", personDTO, houseDTO);
        personService.inhabitHouse(
                beanMappingService.mapTo(houseDTO, House.class),
                beanMappingService.mapTo(personDTO, Person.class)
        );
    }

    @Override
    public boolean authenticate(PersonDTO personDTO, String password) {
        log.debug("Authenticating the person {} with the password {}", personDTO, password);
        Person person = beanMappingService.mapTo(personDTO, Person.class);
        return personService.authenticate(person, password);
    }
}
