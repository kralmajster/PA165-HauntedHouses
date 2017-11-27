package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;
import fi.muni.pa165.hauntedhouses.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class HouseFacadeImpl implements HouseFacade {

    private static final Logger log = LoggerFactory.getLogger(HouseFacadeImpl.class);

    @Inject
    private HouseService houseService;

    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public HouseDTO createHouse(HouseDTO houseDTO) {
        log.debug("Registering houseDto {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        houseService.createHouse(house);
        houseDTO.setId(house.getId());
        return houseDTO;
    }

    @Override
    public void updateHouse(HouseDTO houseDTO) {
        log.debug("Updating houseDto {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        houseService.updateHouse(house);
    }

    @Override
    public void deleteHouse(Long id) {
        log.debug("Deleting house with id {}", id);
        House house = beanMappingService.mapTo(findById(id), House.class);
        houseService.deleteHouse(house);
    }

    @Override
    public HouseDTO findById(Long id) {
        log.debug("Finding house by id: {}", id);
        return  beanMappingService.mapTo(houseService.findById(id), HouseDTO.class);
    }

    @Override
    public HouseDTO findByName(String name) {
        log.debug("Finding house by name: {}", name);
        return  beanMappingService.mapTo(houseService.findByName(name), HouseDTO.class);
    }

    @Override
    public HouseDTO findByAddress(String address) {
        log.debug("Finding house by address: {}", address);
        return  beanMappingService.mapTo(houseService.findByAdress(address), HouseDTO.class);
    }

    @Override
    public List<HouseDTO> findAllHouses() {
        log.debug("Getting all people");
        return  beanMappingService.mapTo(houseService.findAllHouses(), HouseDTO.class);
    }

    @Override
    public boolean isHouseHaunted(HouseDTO houseDTO) {
        log.debug("Checking if house {} is haunted", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return houseService.isHouseHaunted(house);
    }

    @Override
    public boolean isOccupated(HouseDTO houseDTO) {
        log.debug("Checking if house {} is occupated", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return houseService.isOccupated(house);
    }

    @Override
    public List<GhostDTO> getGhosts(HouseDTO houseDTO) {
        log.debug("Getting all ghosts haunting house: {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return  beanMappingService.mapTo(houseService.getGhosts(house), GhostDTO.class);
    }

    @Override
    public List<PersonDTO> getInhabitants(HouseDTO houseDTO) {
        log.debug("Getting all inhabitants in house: {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return  beanMappingService.mapTo(houseService.getInhabitants(house), PersonDTO.class);
    }

    @Override
    public PersonDTO getOwner(HouseDTO houseDTO) {
        log.debug("Getting owner of house: {}", houseDTO);
        House house = beanMappingService.mapTo(houseDTO, House.class);
        return beanMappingService.mapTo(houseService.getOwner(house), PersonDTO.class);
    }
}