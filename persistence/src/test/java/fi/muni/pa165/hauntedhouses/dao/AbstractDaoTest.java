package fi.muni.pa165.hauntedhouses.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import fi.muni.pa165.hauntedhouses.PersistenceApplicationContext;

/**
 *
 * @author Mario Majernik, 422165
 *
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AbstractDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected GhostDao ghostDao;

    @Autowired
    protected AbilityDao abilityDao;

    @Autowired
    protected HouseDao houseDao;

    @Autowired
    protected PersonDao personDao;
    
}
