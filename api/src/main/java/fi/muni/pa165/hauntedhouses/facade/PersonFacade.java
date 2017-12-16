package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.enums.Role;

import java.util.Collection;
import java.util.List;

/**
 * Created by Marek Bohm, 396257.
 */

public interface PersonFacade {

    /**
     * Creates a new person in the system.
     *
     * @param person to be created
     * @return the newly created person
     */
    PersonDTO registerPerson(PersonDTO person);

    /**
     * Removes the given person by her/his ID.
     *
     * @param id of the person to be removed
     */
    void removePerson(Long id);

    /**
     * Updates the given person.
     *
     * @param person the person to be updated
     */
    void updatePerson(PersonDTO person);

    /**
     * Returns a list of all people in the system.
     *
     * @return the list of all people
     */
    List<PersonDTO> getAllPeople();

    /**
     * Authenticates a person in the system.
     *
     * @param username the username of the person to be authenticated
     * @param password the person's password
     * @return true if the authentication was successful, false otherwise
     */
    boolean authenticate(String username, String password);

    /**
     * Checks whether a particular person has the required rights to perform an action.
     *
     * @param person to be used
     * @param accessConstraints the constraints to be checked
     * @return true if the person has the required rights, false otherwise
     */
    boolean isAllowed(PersonDTO person, List<Role> accessConstraints);

    /**
     * Finds a person by her/his ID.
     *
     * @param id the person's ID
     * @return the found person
     */
    PersonDTO findPersonById(Long id);

    /**
     * Finds a person by her/his name.
     *
     * @param name the person's name
     * @return the found person
     */
    List<PersonDTO> findPersonByName(String name);

    /**
     * Finds a person by her/his username in the system.
     *
     * @param username the person's username in the system
     * @return the found person
     */
    PersonDTO findPersonByLogin(String username);

    /**
     * Changes the house where a particular person lives.
     *
     * @param house the house to which the person is moving
     * @param person the person which is moving
     */
    void inhabitHouse(HouseDTO house, PersonDTO person);

}
