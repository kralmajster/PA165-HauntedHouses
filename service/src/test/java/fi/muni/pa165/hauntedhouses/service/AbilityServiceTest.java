package fi.muni.pa165.hauntedhouses.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.dao.AbilityDao;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;
/**
 * 
 * @author Mario Majernik, 422165
 *
 */
public class AbilityServiceTest extends AbstractServiceTest{
	@Mock
	private AbilityDao abilityDao;
	
	@InjectMocks
	@Autowired
	private AbilityServiceImpl abilityService;
	
	private Ability ability1;
	private Ability ability2;
	
	private List<Ability> listOfAbilities;
	
	private List<Ability> emptyList = Collections.emptyList();
	
	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		ability1 = new Ability();
		ability1.setName("Scream");
		ability1.setType(AbilityType.NOISE);
		ability1.setDescription("Awful scream");
		
		ability2 = new Ability();
		ability2.setName("Fire Attack");
		ability2.setType(AbilityType.FIRE);
		ability2.setDescription("Powerfull fire attack");
		
		listOfAbilities = new ArrayList<>();
		listOfAbilities.add(ability1);
		listOfAbilities.add(ability2);		
	}
	
	@Test
    public void findAllWithEmptyListReturnsEmptyListTest() {
        when(abilityDao.findAll()).thenReturn(emptyList);
        
        assertThat(abilityService.findAllAbilities()).isEqualTo(emptyList);
    }
    
    @Test
    public void findAllWithNonEmptyListReturnsNonEmptyListTest() {
        when(abilityDao.findAll()).thenReturn(listOfAbilities);     
        
        assertThat(abilityService.findAllAbilities()).isEqualTo(listOfAbilities);
    }
    
    @Test
    public void findByIdWithWrongIdReturnsNullTest() {
        Long id = 0l;
        when(abilityDao.findById(id)).thenReturn(null);
        
        assertThat(abilityService.findById(id)).isNull();
    }

    @Test
    public void findByIdWithCorrectIdReturnsAbilityTest() {
        Long id = 0l;
        when(abilityDao.findById(id)).thenReturn(ability1);
        
        assertThat(abilityService.findById(id)).isEqualTo(ability1);
    }
    
    @Test 
    public void findByNameReturnsNullTest() {
    	String abilityName = "Fire Attack";
        when(abilityDao.findByName(abilityName)).thenReturn(null);

        assertThat(abilityService.findByName(abilityName)).isNull();
    }
    
    @Test 
    public void findByNameReturnsAbilityTest() {
    	String abilityName = "Fire Attack";
        when(abilityDao.findByName(abilityName)).thenReturn(ability2);

        assertThat(ability2).isEqualTo(abilityService.findByName(abilityName));
    }
    
    @Test
    public void createAbilityTest() {
    	abilityService.createAbility(ability1);
    	verify(abilityDao).create(ability1);
    }
    
    @Test
    public void updateAbilityTest() {
    	abilityService.createAbility(ability1);
    	verify(abilityDao).create(ability1);
    	
    	ability1.setType(AbilityType.DARKNESS);
    	
    	abilityService.updateAbility(ability1);
    	verify(abilityDao).update(ability1);
    }
    
    @Test
    public void deleteAbilityTest() {
    	abilityService.createAbility(ability1);
    	verify(abilityDao).create(ability1);
    	
    	abilityService.deleteAbility(ability1);
    	verify(abilityDao).remove(ability1);
    }
}
