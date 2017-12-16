package fi.muni.pa165.hauntedhouses.controllers;

import fi.muni.pa165.hauntedhouses.ApiContract;
import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceConflict;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotFound;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotValid;
import fi.muni.pa165.hauntedhouses.facade.AbilityFacade;
import fi.muni.pa165.hauntedhouses.facade.GhostFacade;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Adam Dobias
 */

public class AbilityController {

    private static final String ID = ApiContract.Ability.ID;
    private static final String PATH_ID = ApiContract.Ability.PATH_ID;

    @Inject
    AbilityFacade abilityFacade;
    
    @Inject
    GhostFacade ghostFacade;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAbility(@Valid @RequestBody AbilityDTO ability, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            abilityFacade.createAbility(ability);
        } catch (DataAccessException e) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ID, method = RequestMethod.DELETE)
    public void deleteAbility(@PathVariable(PATH_ID) Long id) {
        try {
            abilityFacade.deleteAbility(id);
        } catch (DataAccessException e) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AbilityDTO getAbilityById(@PathVariable(PATH_ID) Long id) {

        AbilityDTO a = abilityFacade.findById(id);
        if (a != null) {
            return a;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AbilityDTO> getAllAbilities() {
        List<AbilityDTO> abilities = abilityFacade.findAllAbilities();

        return abilities == null ? Collections.emptyList() : abilities;
    }
    
    @RequestMapping(value = ApiContract.Ability.GHOSTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GhostDTO> getGhostsWithAbility(@PathVariable(ApiContract.Ability.PATH_ID) long id) {
        AbilityDTO ability = abilityFacade.findById(id);
        if (ability == null) {
            throw new ResourceNotFound();
        }
        
        List<GhostDTO> ghostsWithAbility = ghostFacade.findByAbility(ability);
        if (ghostsWithAbility == null) {
            ghostsWithAbility = Collections.emptyList();
        }
        
        return ghostsWithAbility;
    }

    // TODO update methods - see how pages will turn out
    // TODO pages for displaying abilities, creating and updating (these two will be basically the same)
}
