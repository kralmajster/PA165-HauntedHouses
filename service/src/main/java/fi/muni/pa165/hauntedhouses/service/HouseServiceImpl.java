package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.dao.HouseDao;
import fi.muni.pa165.hauntedhouses.dao.PersonDao;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Adam Dobias
 */

@Service
public class HouseServiceImpl implements HouseService {

    @Inject
    private HouseDao houseDao;

    @Inject
    private PersonDao personDao;

    @Override
    public List<House> findAllHouses() throws DataAccessException {
        return houseDao.findAll();
    }

    @Override
    public House findByName(String name) throws DataAccessException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or an empty string!");
        }
        return houseDao.findByName(name);
    }

    @Override
    public House findByAdress(String adress) throws DataAccessException, IllegalArgumentException {
        if (adress == null || adress.isEmpty()) {
            throw new IllegalArgumentException("The adress cannot be null or an empty string!");
        }
        for (House h : houseDao.findAll()) {
            if (h.getAddress().equals(adress)) {
                return h;
            }
        }
        return null;
    }

    @Override
    public House findById(Long id) throws DataAccessException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("The ID cannot be null!");
        }
        return houseDao.findByID(id);
    }

    @Override
    public void updateHouse(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house to be updated cannot be null!");
        }
        houseDao.update(house);
    }

    @Override
    public void deleteHouse(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house to be deleted cannot be null!");
        }
        houseDao.remove(house);
    }

    @Override
    public void createHouse(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house to be created cannot be null!");
        }
        houseDao.create(house);
    }

    @Override
    public boolean isHouseHaunted(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house cannot be null!");
        }
        return !(house.getGhosts().isEmpty());
    }

    @Override
    public boolean isOccupated(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house cannot be null!");
        }
        return !(house.getResidents().isEmpty());
    }

    @Override
    public List<Ghost> getGhosts(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house cannot be null!");
        }
        return new ArrayList<>(house.getGhosts());
    }

    @Override
    public List<Person> getInhabitants(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house cannot be null!");
        }
        return new ArrayList<>(house.getResidents());
    }

    @Override
    public Person getOwner(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("The house cannot be null!");
        }
        return personDao.findById(house.getOwnerID());
    }

}
