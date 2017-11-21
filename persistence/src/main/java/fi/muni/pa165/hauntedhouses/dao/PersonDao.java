package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.Person;

import java.util.List;

/**
 * @author Marek Bohm, 396257
 */

public interface PersonDao {

    /**
     * Creates a persistent representation of a person in the database.
     *
     * @param person the person to be persisted
     */
    public void create(Person person);

    /**
     * Updates a person in the database.
     *
     * @param person the person to be updated
     */
    public void update(Person person);

    /**
     * Deletes a person from the database.
     *
     * @param person the person to be deleted
     */
    public void remove(Person person);

    /**
     * Finds a person in the database by the given ID.
     *
     * @param id the ID of the person to be found
     * @return the person with the given ID
     */
    public Person findById(Long id);

    /**
     * Finds a person in the database by the given login.
     *
     * @param login the login of the person to be found
     * @return the person with the given login
     */
    public Person findPersonByLogin(String login);

    /**
     * Retrieves a list of all people from the database.
     *
     * @return a list of all people from the database
     */
    public List<Person> findAll();
}
