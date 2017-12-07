package fi.muni.pa165.hauntedhouses.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;

/**
 * @author Marek Bohm, 396257
 */

public class AbilityDaoTest extends AbstractDaoTest {

    private Ability trapAbility;

    @BeforeMethod
    public void setUp() {
        trapAbility = new Ability();
        trapAbility.setName("Trap ability 3000");
        trapAbility.setType(AbilityType.TRAP);
        abilityDao.create(trapAbility);
    }

    @Test
    public void testCreate() {
        Ability newAbility = new Ability();
        newAbility.setName("Fire in the hole");
        newAbility.setType(AbilityType.FIRE);
        abilityDao.create(newAbility);
        
        assertThat(abilityDao.findById(newAbility.getId())).isNotNull();
        
        abilityDao.remove(newAbility);
    }

    @Test
    public void testUpdate() {
        trapAbility.setName("Trap ability 1000");
        abilityDao.update(trapAbility);
        List<Ability> abilities = abilityDao.findAll();

        assertThat(abilities.size()).isEqualTo(1);
        assertThat(abilities.get(0).getName()).isEqualTo(trapAbility.getName());
    }

    @Test
    public void testRemove() {
        assertThat(abilityDao.findById(trapAbility.getId())).isNotNull();
        abilityDao.remove(trapAbility);
        assertThat(abilityDao.findById(trapAbility.getId())).isNull();
        abilityDao.create(trapAbility);
    }

    @Test
    public void testFindByID() {
        Ability testAbility = abilityDao.findById(trapAbility.getId());
        
        assertThat(testAbility.getName()).isEqualTo("Trap ability 3000");
        assertThat(testAbility.getType()).isEqualTo(AbilityType.TRAP);
    }

    @Test
    public void testFindByName() {
        assertThat(abilityDao.findByName("Trap ability 3000")).isEqualTo(trapAbility);
        assertThat(abilityDao.findByName("Trap ability 3001")).isNull();
    }

    @Test
    public void testFindAll() {
        List<Ability> abilities = abilityDao.findAll();
        assertThat(abilities).hasSize(1).contains(trapAbility);
    }

}
