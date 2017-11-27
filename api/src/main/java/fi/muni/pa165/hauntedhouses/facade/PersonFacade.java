package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.enums.Role;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Marek Bohm
 */
public interface PersonFacade {

    /**
     * Registers new person in the system
     * @param p - PersonDTO
     * @return newly created person
     */
    PersonDTO registerPerson(PersonDTO p);

    /**
     * Removes given person
     * @param id - user's id
     */
    void removePerson(Long id);

    /**
     * Update given person
     * @param p persom to be updated
     */
    void updatePerson(PersonDTO p);

    /**
     * Get all people
     *
     * @return Collection<PersonDTO> people
     */
    Collection<PersonDTO> getAllPeople();

    /**
     * Authenticate person in the system
     *
     * @param p - PersonDTO
     * @param password - user's password
     * @return true if the authentication was successful
     */
    boolean authenticate(PersonDTO p, String password);

    /**
     * Check wheter a person has is allowed for action
     *
     * @param u - PersonDTO
     * @return true if person is allowed for action, else false
     */
    boolean isAllowed(PersonDTO u, List<Role> acessConstraint);

    /**
     * Find person by id
     *
     * @param id - user's id
     * @return found person or null
     */
    PersonDTO findPersonById(Long id);

    /**
     * Find person by name
     *
     * @param name - user's login
     * @return found person or null
     */
    PersonDTO findPersonByName(String name);

    /**
     * Person's current house will be changed
     * @param house - HouseDTO which should be inhabited
     * @param person - PersonDTO which should be moving
     */
    void inhabitHouse(HouseDTO house, PersonDTO person);




}
