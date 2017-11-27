package fi.muni.pa165.hauntedhouses.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.dao.HouseDao;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
/**
 * 
 * @author Mario Majernik, 422165
 *
 */
public class HouseServiceTest extends AbstractServiceTest{
	@Mock
	private HouseDao houseDao;
	
	@InjectMocks
	@Autowired
	private HouseServiceImpl houseService;
	
	private House house1;
	private House house2;
	
	private Person person1;
	private Person person2;
	
	private Ghost ghost;
	
	private Set<Person> residents;
	private Set<Ghost> ghosts;
	
	private List<House> listOfHouses;
	
	private List<House> emptyList = Collections.emptyList();
	
	
	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		person1 = new Person();
		person1.setLogin("Peter123");
		person1.setName("Peter");
		person1.setPasswordHash("sd#l1");
		
		person2 = new Person();
		person2.setLogin("Vito998");
		person2.setName("Viktor");
		person2.setPasswordHash("gf89fd@");
		
		residents = new HashSet<>();
		residents.add(person1);
		residents.add(person2);
		
		ghost = new Ghost();
		ghost.setName("Witcher");
		ghost.setDescription("Very powerful witch ghost");
		ghost.setHauntFrom(new Date(2));
		ghost.setHauntTo(new Date(4));
		ghost.setHauntReason("Because it is witch");
		
		ghosts = new HashSet<>();
		ghosts.add(ghost);
		
		house1 = new House();
		house1.setAddress("Main 1");
		house1.setBecameHauntedDate(new Date(5));
		house1.setGhosts(ghosts);
		house1.setHistory("Very old house");
		house1.setName("My house");
		house1.setOwnerID(4l);
		house1.setResidents(residents);
		
		house2 = new House();
		house2.setAddress("Jostova");
		house2.setBecameHauntedDate(new Date(23));
		house2.setHistory("Very big old house with pool");
		house2.setName("Her big house");
		house2.setOwnerID(6l);
	}
	
	@Test
    public void findAllWithEmptyListReturnsEmptyListTest() {
        when(houseDao.findAll()).thenReturn(emptyList);

        assertThat(houseService.findAllHouses()).isEqualTo(emptyList);
    }
    
    @Test
    public void findAllWithNonEmptyListReturnsNonEmptyListTest() {
        when(houseDao.findAll()).thenReturn(listOfHouses);       
        
        assertThat(houseService.findAllHouses()).isEqualTo(listOfHouses);
    }
    
    @Test
    public void findByIdWithWrongIdReturnsNullTest() {
        Long id = 0l;
        when(houseDao.findByID(id)).thenReturn(null);
      
        assertThat(houseService.findById(id)).isNull();
    }

    @Test
    public void findByIdWithCorrectIdReturnsHouseTest() {
        Long id = 0l;
        when(houseDao.findByID(id)).thenReturn(house1);
       
        assertThat(houseService.findById(id)).isEqualTo(house1);
    }
    
    @Test 
    public void findByNameReturnsNullTest() {
    	String houseName = "Her big house";
        when(houseDao.findByName(houseName)).thenReturn(null);

        assertThat(houseService.findByName(houseName)).isNull();
    }
    
    @Test 
    public void findByNameReturnsHouseTest() {
    	String houseName = "Her big house";
        when(houseDao.findByName(houseName)).thenReturn(house2);

        assertThat(house2).isEqualTo(houseService.findByName(houseName));
    }

    @Test
    public void createHouseTest() {
    	houseService.createHouse(house1);
    	verify(houseDao).create(house1);
    }
    
    @Test
    public void updateHouseTest() {
    	houseService.createHouse(house1);
    	verify(houseDao).create(house1);
    	
    	house1.setHistory("Alternative history");
    	
    	houseService.updateHouse(house1);
    	verify(houseDao).update(house1);    	
    }
    
    @Test
    public void deleteHouseTest() {
    	houseService.createHouse(house1);
    	verify(houseDao).create(house1);
    	
    	houseService.deleteHouse(house1);
    	verify(houseDao).remove(house1);	
    }

}