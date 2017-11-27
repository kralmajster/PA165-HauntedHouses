package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;
import fi.muni.pa165.hauntedhouses.service.GhostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class GhostFacadeImpl implements GhostFacade {

    private static final Logger log = LoggerFactory.getLogger(GhostFacadeImpl.class);

    @Inject
    private GhostService ghostService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public GhostDTO createGhost(GhostDTO ghostDTO) {
        log.debug("Registering ghostDTO {}", ghostDTO);
        Ghost ghost = beanMappingService.mapTo(ghostDTO, Ghost.class);
        ghostService.createGhost(ghost);
        ghostDTO.setId(ghost.getId());
        return ghostDTO;    }

    @Override
    public void updateGhost(GhostDTO ghostDTO) {
        log.debug("Updating ghostDto {}", ghostDTO);
        Ghost ghost = beanMappingService.mapTo(ghostDTO, Ghost.class);
        ghostService.updateGhost(ghost);
    }

    @Override
    public void deleteGhost(Long id) {
        log.debug("Deleting ghost with id {}", id);
        Ghost ghost = beanMappingService.mapTo(findById(id), Ghost.class);
        ghostService.deleteGhost(ghost);
    }

    @Override
    public GhostDTO findById(Long id) {
        log.debug("Finding ghost by id: {}", id);
        return  beanMappingService.mapTo(ghostService.findById(id), GhostDTO.class);
    }

    @Override
    public GhostDTO findByName(String name) {
        log.debug("Finding ghost by name: {}", name);
        return  beanMappingService.mapTo(ghostService.findByName(name), GhostDTO.class);
    }

    @Override
    public List<GhostDTO> findAllGhosts() {
        log.debug("Getting all people");
        return  beanMappingService.mapTo(ghostService.findAll(), GhostDTO.class);
    }

    @Override
    public List<GhostDTO> findByAbility(AbilityDTO abilityDTO) {
        log.debug("Finding ghosts by abilityDTO: {}", abilityDTO);
        Ability ability = beanMappingService.mapTo(abilityDTO, Ability.class);
        return  beanMappingService.mapTo(ghostService.findByAbility(ability), GhostDTO.class);
    }

    @Override
    public void giveAbility(GhostDTO ghostDTO, AbilityDTO abilityDTO) {
        log.debug("Giving ability {} to ghost {}", abilityDTO, ghostDTO);
        ghostService.giveAbility(
                beanMappingService.mapTo(ghostDTO, Ghost.class),
                beanMappingService.mapTo(abilityDTO, Ability.class)
        );
    }

    @Override
    public void removeAbility(GhostDTO ghostDTO, AbilityDTO abilityDTO) {
        log.debug("Removing ability {} from ghost {}", abilityDTO, ghostDTO);
        ghostService.removeAbility(
                beanMappingService.mapTo(ghostDTO, Ghost.class),
                beanMappingService.mapTo(abilityDTO, Ability.class)
        );
    }

    @Override
    public void hauntHouse(HouseDTO houseDTO, GhostDTO ghostDTO) {
        log.debug("Haunting house {} with ghost {}", houseDTO, ghostDTO);
        ghostService.hauntHouse(
                beanMappingService.mapTo(houseDTO, House.class),
                beanMappingService.mapTo(ghostDTO, Ghost.class)
        );
    }

    @Override
    public boolean isGhostHaunting(GhostDTO ghostDTO) {
        log.debug("Checking if ghost {} is haunting", ghostDTO);
        Ghost ghost = beanMappingService.mapTo(ghostDTO, Ghost.class);
        return ghostService.isGhostHaunting(ghost);
    }
}
