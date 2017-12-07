package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Adam Dobias
 */

@Service
public interface HouseService {

    /**
     * Returns a list of all houses.
     * 
     * @return a list of all houses in the database
     * @throws DataAccessException in case of any failure on the persistence layer
     */
    List<House> findAllHouses() throws DataAccessException;

    /**
     * Finds a house based on its name.
     * 
     * @param name of the house to be found
     * @return the house with the matching name
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the name is null or an empty string
     */
    House findByName(String name) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a house based on its address.
     * 
     * @param adress of the house to be found
     * @return the house that is located at this address, null if the house was not found
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the address is null or an empty string
     */
    House findByAdress(String adress) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a house based on its ID.
     * 
     * @param id of the house to be found
     * @return the house with the given ID
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ID is null or the house is not present in the database
     */
    House findById(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Updates a house.
     * 
     * @param house to be updated
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    void updateHouse(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Removes a house.
     * 
     * @param house to be removed
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    void deleteHouse(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Creates a house.
     * 
     * @param house to be created
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    void createHouse(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Checks whether any ghosts live in a particular house.
     * 
     * @param house to be used
     * @return true if the house is haunted, false otherwise
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    boolean isHouseHaunted(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Checks whether any people live in a particular house.
     * 
     * @param house to be used
     * @return true if there are any tenants in the house, false otherwise
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    boolean isOccupated(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns a list of all ghosts haunting in a particular house.
     * 
     * @param house to be used
     * @return a list of all ghosts haunting in this house
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    List<Ghost> getGhosts(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns a list of all people living in a particular house.
     * 
     * @param house to be used
     * @return a list of all people living in this house
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    List<Person> getInhabitants(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns an owner of a particular house.
     * 
     * @param house to be used
     * @return an owner of this house
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the house is null or not present in the database
     */
    Person getOwner(House house) throws DataAccessException, IllegalArgumentException;

}
