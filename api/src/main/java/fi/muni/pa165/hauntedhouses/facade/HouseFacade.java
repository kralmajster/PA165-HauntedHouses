package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;

import java.util.List;


/**
 * Created by Marek Bohm 396257
 */
public interface HouseFacade {

    /**
     * Create new house
     * @param h house to be created
     * @return id of new house
     */
    HouseDTO createHouse(HouseDTO h);

    /**
     * Update given house
     * @param h house to be updated
     */
    void updateHouse(HouseDTO h);

    /**
     * Delete house with given id
     * @param id of house to be deleted
     */
    void deleteHouse(Long id);

    /**
     * Find house with given id
     * @param id of house to be found
     * @return house with given id
     */
    HouseDTO findById(Long id);

    /**
     * Find house with given name
     * @param name of house to be found
     * @return house with given name
     */
    HouseDTO findByName(String name);

    /**
     * Find house with given address
     * @param address of house to be found
     * @return house with given address
     */
    HouseDTO findByAddress(String address);

    /**
     * Find all houses.
     * @return List of all houses.
     */
    List<HouseDTO> findAllHouses();

    /**
     * @param h HouseDTO
     * @return true if is haunted, else false
     */
    boolean isHouseHaunted(HouseDTO h);

    /**
     * @param h HouseDTO
     * @return true if has tenants, else false
     */
    boolean isOccupated(HouseDTO h);

    /**
     * @param h HouseDTO
     * @return list of all ghosts haunting given house
     */
    List<GhostDTO> getGhosts(HouseDTO h);

    /**
     * @param h HouseDTO
     * @return list of all people living in given house
     */
    List<PersonDTO> getInhabitants(HouseDTO h);

    /**
     * @param h HouseDTO
     * @return PersonDTO - owner of given house
     */
    PersonDTO getOwner(HouseDTO h);
}
