package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.service.AbilityService;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class AbilityFacadeImpl implements AbilityFacade {

    private static final Logger log = LoggerFactory.getLogger(AbilityFacadeImpl.class);

    @Inject
    private AbilityService abilityService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public AbilityDTO createAbility(AbilityDTO abilityDTO) {
        log.debug("Creating abilityDto {}", abilityDTO);
        Ability ability = beanMappingService.mapTo(abilityDTO, Ability.class);
        abilityService.createAbility(ability);
        abilityDTO.setId(ability.getId());
        return abilityDTO;
    }

    @Override
    public void updateAbility(AbilityDTO abilityDTO) {
        log.debug("Updating abilityDto {}", abilityDTO);
        Ability ability = beanMappingService.mapTo(abilityDTO, Ability.class);
        abilityService.updateAbility(ability);
    }

    @Override
    public void deleteAbility(Long id) {
        log.debug("Deleting ability with id {}", id);
        Ability ability = beanMappingService.mapTo(findById(id), Ability.class);
        abilityService.deleteAbility(ability);
    }

    @Override
    public AbilityDTO findById(Long id) {
        log.debug("Finding ability by id: {}", id);
        return  beanMappingService.mapTo(abilityService.findById(id), AbilityDTO.class);
    }

    @Override
    public AbilityDTO findByName(String name) {
        log.debug("Finding ability by name: {}", name);
        return  beanMappingService.mapTo(abilityService.findByName(name), AbilityDTO.class);
    }

    @Override
    public List<AbilityDTO> findAllAbilities() {
        log.debug("Getting all people");
        return  beanMappingService.mapTo(abilityService.findAllAbilities(), AbilityDTO.class);
    }
}
