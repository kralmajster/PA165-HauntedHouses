package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;

import java.util.List;

/**
 * Created by Marek Bohm, 396257.
 */

public interface HouseFacade {

    /**
     * Creates a new house.
     *
     * @param house the house to be created
     * @return the newly created house
     */
    HouseDTO createHouse(HouseDTO house);

    /**
     * Updates the given house.
     *
     * @param house the house to be updated
     */
    void updateHouse(HouseDTO house);

    /**
     * Deletes the house with the given ID.
     *
     * @param id of the house to be deleted
     */
    void deleteHouse(Long id);

    /**
     * Finds the house with the given ID.
     *
     * @param id of the house to be found
     * @return the house with the given ID
     */
    HouseDTO findById(Long id);

    /**
     * Finds the house with the given name.
     *
     * @param name of the house to be found
     * @return the house with the given name
     */
    HouseDTO findByName(String name);

    /**
     * Finds the house with the given address.
     *
     * @param address of the house to be found
     * @return the house with the given address
     */
    HouseDTO findByAddress(String address);

    /**
     * Finds all houses.
     *
     * @return the list of all houses
     */
    List<HouseDTO> findAllHouses();

    /**
     * Determines whether a particular house is haunted by ghosts.
     * 
     * @param house to be used
     * @return true if the house is haunted, false otherwise
     */
    boolean isHouseHaunted(HouseDTO house);

    /**
     * Determines whether a particular house is occupied by tenants.
     * 
     * @param house to be used
     * @return true if the house has tenants, false otherwise
     */
    boolean isOccupied(HouseDTO house);

    /**
     * Returns a list of ghosts that are haunting a particular house.
     * 
     * @param house to be used
     * @return the list of all ghosts that are haunting the given house
     */
    List<GhostDTO> getGhosts(HouseDTO house);

    /**
     * Returns a list of all people that are living in a particular house.
     * 
     * @param house to be used
     * @return the list of all people that are living in the given house
     */
    List<PersonDTO> getInhabitants(HouseDTO house);

    /**
     * Returns the owner of the given house.
     * 
     * @param house to be used
     * @return the owner of the given house
     */
    PersonDTO getOwner(HouseDTO house);
}
