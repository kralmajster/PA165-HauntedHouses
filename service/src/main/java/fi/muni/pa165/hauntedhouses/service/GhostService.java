package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Adam Dobias
 */

@Service
public interface GhostService {

    /**
     * Finds a ghost based on its ID.
     * 
     * @param id of the ghost to be found
     * @return the ghost with the matching ID
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the used ID is null
     */
    Ghost findById(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a ghost based on its name.
     * 
     * @param name of the ghost to be found
     * @return the ghost with the matching name
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the name is null or an empty string
     */
    Ghost findByName(String name) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns a list of all ghosts.
     * 
     * @return a list of all ghosts in the database
     * @throws DataAccessException in case of any failure on the persistence layer
     */
    List<Ghost> findAll() throws DataAccessException;

    /**
     * Removes a ghost.
     * 
     * @param ghost to be removed
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ghost is null
     */
    void deleteGhost(Ghost ghost) throws DataAccessException, IllegalArgumentException;

    /**
     * Updates a ghost.
     * 
     * @param ghost to be updated
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ghost is null
     */
    void updateGhost(Ghost ghost) throws DataAccessException, IllegalArgumentException;

    /**
     * Creates a ghost.
     * 
     * @param ghost to be created
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ghost is null
     */
    void createGhost(Ghost ghost) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds all the ghosts that have a particular ability.
     * 
     * @param ability to be used for searching
     * @return a list of all the ghosts that have the used ability
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the used ability is null
     */
    List<Ghost> findByAbility(Ability ability) throws DataAccessException, IllegalArgumentException;

    /**
     * Gives a particular ability to a ghost.
     * 
     * @param ghost to which the ability will be given
     * @param ability which will be given
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ghost or the ability are null or not present in the database
     */
    void giveAbility(Ghost ghost, Ability ability) throws DataAccessException, IllegalArgumentException;

    /**
     * Removes an ability from a ghost.
     * 
     * @param ghost which will loose the ability
     * @param ability which will be lost
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ghost or the ability are null or not present in the database
     */
    void removeAbility(Ghost ghost, Ability ability) throws DataAccessException, IllegalArgumentException;

    /**
     * Changes the house where a particular ghost lives.
     *
     * @param house the house where the ghost is moving
     * @param ghost that is moving
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ghost or the house are null or not present in the database
     */
    void hauntHouse(House house, Ghost ghost) throws DataAccessException, IllegalArgumentException;

    /**
     * Checks whether a particular ghost is haunting at the moment.
     * 
     * @param ghost to be used
     * @return true if the ghost is haunting at the moment, false otherwise
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ghost is null or not present in the database
     */
    boolean isGhostHaunting(Ghost ghost) throws DataAccessException, IllegalArgumentException;

}
