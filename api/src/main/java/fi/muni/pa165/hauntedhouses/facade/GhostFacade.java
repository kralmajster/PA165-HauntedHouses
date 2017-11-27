package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;

import java.util.List;

/**
 * Created by Marek Bohm 396257
 */
public interface GhostFacade {
    /**
     * Create new ghost
     * @param g ghost to be created
     * @return id of new ghost
     */
    Long createGhost(GhostDTO g);

    /**
     * Update given ghost
     * @param g ghost to be updated
     */
    void updateGhost(GhostDTO g);

    /**
     * Delete ghost with given id
     * @param id of ghost to be deleted
     */
    void deleteGhost(Long id);

    /**
     * Find ghost with given id
     * @param id of ghost to be found
     * @return ghost with given id
     */
    GhostDTO findById(Long id);

    /**
     * Find ghost with given name
     * @param name of ghost to be found
     * @return ghost with given name
     */
    GhostDTO findByName(String name);

    /**
     * Find all ghosts.
     * @return List of all ghosts.
     */
    List<GhostDTO> findAllGhosts();

    /**
     * Find ghosts with given ability
     * @param ability of ghosts to be found
     * @return List<GhostDTO> - ghosts possessing given ability
     */
    List<GhostDTO> findByAbility(String ability);

    /**
     * Grants ability to given ghost
     * @param g to which ability will be given
     * @param a which will be granted
     */
    void giveAbility(GhostDTO g, AbilityDTO a);

    /**
     * Revokes ability from given ghost
     * @param g from which ability will be removed
     * @param a which will be revoked
     */
    void removeAbility(GhostDTO g, AbilityDTO a);

    /**
     * Ghost's house will be changed
     * @param h house where ghost should haunt
     * @param g ghost which should be haunting
     */
    void hauntHouse(HouseDTO h, GhostDTO g);

    /**
     * @param g ghost of concern
     * @return true if is currently haunting, false otherwise
     */
    boolean isGhostHaunting(GhostDTO g);
    
}
