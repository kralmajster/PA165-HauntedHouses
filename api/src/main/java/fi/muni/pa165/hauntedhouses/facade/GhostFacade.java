package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;

import java.util.List;

/**
 * Created by Marek Bohm, 396257.
 */

public interface GhostFacade {

    /**
     * Creates a new ghost.
     *
     * @param ghost the ghost to be created
     * @return the newly created ghost
     */
    GhostDTO createGhost(GhostDTO ghost);

    /**
     * Updates the given ghost.
     *
     * @param ghost the ghost to be updated
     */
    void updateGhost(GhostDTO ghost);

    /**
     * Deletes the ghost with the given ID.
     *
     * @param id of the ghost to be deleted
     */
    void deleteGhost(Long id);

    /**
     * Finds the ghost with the given ID.
     *
     * @param id of the ghost to be found
     * @return the ghost with the given ID
     */
    GhostDTO findById(Long id);

    /**
     * Finds the ghost with the given name.
     *
     * @param name of the ghost to be found
     * @return the ghost with the given name
     */
    GhostDTO findByName(String name);

    /**
     * Finds all ghosts.
     *
     * @return list of all ghosts
     */
    List<GhostDTO> findAllGhosts();

    /**
     * Finds all ghosts with the given ability.
     *
     * @param ability by which we want to find the ghosts
     * @return the list of ghosts with the given ability
     */
    List<GhostDTO> findByAbility(AbilityDTO ability);

    /**
     * Gives a particular ability to a ghost.
     *
     * @param ghost to which the ability will be given
     * @param ability the ability to be given
     */
    void giveAbility(GhostDTO ghost, AbilityDTO ability);

    /**
     * Removes a single ability from a particular ghost.
     *
     * @param ghost which will lose an ability
     * @param ability to be removed
     */
    void removeAbility(GhostDTO ghost, AbilityDTO ability);

    /**
     * Changes the house where a particular ghost lives.
     *
     * @param house the new house where the ghost moves
     * @param ghost to be moved
     */
    void hauntHouse(HouseDTO house, GhostDTO ghost);

    /**
     * Determines whether a particular ghost is currently haunting.
     * 
     * @param ghost to be used
     * @return true if the ghost is currently haunting, false otherwise
     */
    boolean isGhostHaunting(GhostDTO ghost);

}
