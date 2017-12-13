package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseCreateDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;
import fi.muni.pa165.hauntedhouses.service.HouseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marek Bohm, 396257
 */

@Service
@Transactional
public class HouseFacadeImpl implements HouseFacade {

    private static final Logger log = LoggerFactory.getLogger(HouseFacadeImpl.class);

    @Inject
    private HouseService houseService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createHouse(HouseDTO houseDTO) {
        log.debug("Creating the house {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        houseService.createHouse(house);
    }
    
    @Override
    public void buildHouse(HouseCreateDTO houseCreateDTO) {
        log.debug("Building the house {}", houseCreateDTO);
        houseService.buildHouse(beanMappingService.mapTo(houseCreateDTO, House.class), houseCreateDTO.getOwnerID());
    }

    @Override
    public void updateHouse(HouseDTO houseDTO) {
        log.debug("Updating the house {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        houseService.updateHouse(house);
    }

    @Override
    public void deleteHouse(Long id) {
        log.debug("Deleting the house with the ID {}", id);
        House house = beanMappingService.mapTo(findById(id), House.class);
        houseService.deleteHouse(house);
    }

    @Override
    public HouseDTO findById(Long id) {
        log.debug("Finding a house by the ID {}", id);
        return beanMappingService.mapTo(houseService.findById(id), HouseDTO.class);
    }

    @Override
    public HouseDTO findByName(String name) {
        log.debug("Finding a house by the name {}", name);
        return beanMappingService.mapTo(houseService.findByName(name), HouseDTO.class);
    }

    @Override
    public HouseDTO findByAddress(String address) {
        log.debug("Finding a house by the address {}", address);
        return beanMappingService.mapTo(houseService.findByAdress(address), HouseDTO.class);
    }

    @Override
    public List<HouseDTO> findAllHouses() {
        log.debug("Fetching all people");
        List<House> houses = houseService.findAllHouses();
        return houses == null ? null : beanMappingService.mapTo(houses, HouseDTO.class);
    }

    @Override
    public boolean isHouseHaunted(HouseDTO houseDTO) {
        log.debug("Checking whether the house {} is haunted", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return houseService.isHouseHaunted(house);
    }

    @Override
    public boolean isOccupied(HouseDTO houseDTO) {
        log.debug("Checking whether any people live in the house {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return houseService.isOccupated(house);
    }

    @Override
    public List<GhostDTO> getGhosts(HouseDTO houseDTO) {
        log.debug("Fetching all the ghosts that are haunting the house {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return beanMappingService.mapTo(houseService.getGhosts(house), GhostDTO.class);
    }

    @Override
    public List<PersonDTO> getInhabitants(HouseDTO houseDTO) {
        log.debug("Fetching all the tenants that are living in the house {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return beanMappingService.mapTo(houseService.getInhabitants(house), PersonDTO.class);
    }

    @Override
    public PersonDTO getOwner(HouseDTO houseDTO) {
        log.debug("Finding the owner of the house {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return beanMappingService.mapTo(houseService.getOwner(house), PersonDTO.class);
    }
}
