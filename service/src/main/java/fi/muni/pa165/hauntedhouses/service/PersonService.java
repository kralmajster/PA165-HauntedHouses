package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.Role;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Adam Dobias
 */

@Service
public interface PersonService {

    /**
     * Creates a new person.
     * 
     * @param person to be created
     * @param password to the person's account, the password will be encrypted
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the person is null
     */
    void registerPerson(Person person, String password) throws DataAccessException, IllegalArgumentException;

    /**
     * Removes a person.
     * 
     * @param person to be removed
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the person is null
     */
    void removePerson(Person person) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns a list of all people in the database.
     * 
     * @return a list of all people, null if there are no people in the database
     * @throws DataAccessException in case on any failure on the persistence layer
     */
    List<Person> getAllPeople() throws DataAccessException;

    /**
     * Authenticates a person in the system.
     * 
     * @param person to be authenticated
     * @param password to be used for the authentication
     * @return true if the password hash matches the corresponding record in the database, false otherwise
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the person is null
     */
    boolean authenticate(Person person, String password) throws DataAccessException, IllegalArgumentException;

    /**
     * Checks whether a particular person has the required rights to perform an action in the system.
     * 
     * @param person to be used
     * @param acessConstraint the roles needed for performing the action
     * @return true if the person has the required rights, false otherwise
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if either the person or the list of roles is null or if the list is empty
     */
    boolean isAllowed(Person person, List<Role> acessConstraint) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a person based on her/his ID.
     * 
     * @param id of the person to be found
     * @return the person with the given ID, null if such person does not exist
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ID is null
     */
    Person findPersonById(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a person based on her/his name.
     * 
     * @param name of the person to be found
     * @return a list of all people that have a matching name (surname, or login)
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the name is null or an empty string
     */
    List<Person> findPersonByName(String name) throws DataAccessException, IllegalArgumentException;

    /**
     * Updates a person.
     * 
     * @param person to be updated
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the person is null
     */
    void updatePerson(Person person) throws DataAccessException, IllegalArgumentException;

    /**
     * Changes the house where a particular person lives.
     *
     * @param house to which the person is moving
     * @param person which is be moving
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if either the person or the house is null or not present in the database
     */
    void inhabitHouse(House house, Person person) throws DataAccessException, IllegalArgumentException;

}
