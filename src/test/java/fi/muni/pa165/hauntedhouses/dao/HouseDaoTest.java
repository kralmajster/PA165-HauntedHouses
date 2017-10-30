package fi.muni.pa165.hauntedhouses.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.PersistenceApplicationContext;
import fi.muni.pa165.hauntedhouses.entity.House;

/**
 * 
 * @author Mario Majernik, 422165
 *
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HouseDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HouseDao houseDao;

    private House testHouse1;
    private House testHouse2;

    @BeforeMethod
    public void initTests() {
    	testHouse1 = new House();
        testHouse1.setName("The Best House Ever");
        testHouse1.setAddress("Lost 3");
        testHouse1.setBecameHauntedDate(new Date(5));
        testHouse1.setHistory("Very old house");
        testHouse1.setOwnerID(10l);

        testHouse2 = new House();
        testHouse2.setName("The Second Best House Ever");
        testHouse2.setAddress("Lost 10");
        testHouse2.setBecameHauntedDate(new Date(13));
        testHouse2.setHistory("Old house");
        testHouse2.setOwnerID(20l);

        houseDao.create(testHouse1);
        houseDao.create(testHouse2);
    }

    @Test(dependsOnMethods = { "getHouseByNameTest" })
    public void createHouseTest() {
        House house = new House();
        house.setName("First House");
        house.setAddress("Find 1");
        house.setBecameHauntedDate(new Date(2));
        house.setHistory("new house");

        houseDao.create(house);
        House retHouse = houseDao.findByName("First House");

        assertThat(retHouse).isNotNull();
        assertThat(retHouse).isEqualTo(house);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createHouseWithNullNameTest() {
        House house = new House();
        house.setAddress("Find 1");
        house.setBecameHauntedDate(new Date(2));
        house.setHistory("new house");

        houseDao.create(house);
    }

    @Test(expectedExceptions = ConstraintViolationException.class , dependsOnMethods = { "getAllHousesTest" })
    public void createHouseWithNullAdressTest() {
        House house = new House();
        house.setName("First House");
        house.setBecameHauntedDate(new Date(2));
        house.setHistory("new house");

        houseDao.create(house);
        houseDao.findAll();
    }

    @Test(dependsOnMethods = { "getAllHousesTest" })
    public void removeHouseTest() {
        List<House> houses = houseDao.findAll();

        assertThat(houses.size()).isEqualTo(2);

        houseDao.remove(testHouse2);
        houses = houseDao.findAll();

        assertThat(houses.size()).isEqualTo(1);
    }

    @Test
    public void getHouseByIdTest() {
        House house = houseDao.findByID(testHouse1.getId());
        assertThat(testHouse1).isEqualTo(house);
    }

    @Test
    public void getHouseByNameTest() {
        House house = houseDao.findByName(testHouse1.getName());
        assertThat(testHouse1).isEqualTo(house);
    }

    @Test
    public void getAllHousesTest() {
        List<House> houses = houseDao.findAll();
        assertThat(houses.size()).isEqualTo(2);
    }

    @Test(dependsOnMethods = { "getAllHousesTest" })
    public void updateHouseTest() {
        testHouse1.setName("new name");
        houseDao.create(testHouse1);
        List<House> houses = houseDao.findAll();

        assertThat(houses.size()).isEqualTo(2);
        assertThat(houses.get(0).getName()).isEqualTo("new name");
    }

}
