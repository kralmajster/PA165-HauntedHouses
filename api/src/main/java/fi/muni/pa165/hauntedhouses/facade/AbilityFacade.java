package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;

import java.util.List;

/**
 * Created by Marek Bohm 396257
 */
public interface AbilityFacade {

    /**
     * Create new ability
     * @param a ability to be created
     * @return id of new ability
     */
    Long createAbility(AbilityDTO a);

    /**
     * Update given ability
     * @param a ability to be updated
     */
    void updateAbility(AbilityDTO a);

    /**
     * Delete ability with given id
     * @param id of ability to be deleted
     */
    void deleteAbility(Long id);

    /**
     * Find ability with given id
     * @param id of ability to be found
     * @return ability with given id
     */
    AbilityDTO findById(Long id);

    /**
     * Find ability with given name
     * @param name of ability to be found
     * @return ability with given name
     */
    AbilityDTO findByName(String name);

    /**
     * Find all abilities.
     * @return List of all abilities.
     */
    List<AbilityDTO> findAllAbilities();
}
