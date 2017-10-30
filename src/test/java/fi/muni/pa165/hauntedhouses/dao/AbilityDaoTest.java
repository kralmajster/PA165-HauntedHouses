package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.PersistenceApplicationContext;
import fi.muni.pa165.hauntedhouses.entity.Ability;

import java.util.List;

import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Marek Bohm, 396257
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AbilityDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public AbilityDao abilityDao;
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
