package fi.muni.pa165.hauntedhouses.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;

/**
 * @author Klara Kufova, 410091
 */

public class GhostDaoTest extends AbstractDaoTest {

    private Ghost zombie, witch, werewolf;

    private House hovel, abandonedFactory, lair;

    @BeforeMethod
    public void setUp() {
        Calendar calendar = Calendar.getInstance();

        hovel = new House();
        hovel.setName("The Hovel");
        hovel.setAddress("Bloodcurdling 666");

        abandonedFactory = new House();
        abandonedFactory.setName("The Abandoned Factory");
        abandonedFactory.setAddress("Nightmarish 13");

        zombie = new Ghost();
        zombie.setName("Larry");
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        zombie.setHauntFrom(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        zombie.setHauntTo(calendar.getTime());
        zombie.setDescription("An undead created through the reanimation of a human corpse.");
        zombie.setHouse(abandonedFactory);

        witch = new Ghost();
        witch.setName("Priscilla");
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        witch.setHauntFrom(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        witch.setHauntTo(calendar.getTime());
        witch.setDescription("A woman practicing black witchcraft with the aid of a devil.");
        witch.setHouse(hovel);

        ghostDao.create(zombie);
        ghostDao.create(witch);
    }

    @Test
    public void testCreate() {
        werewolf = new Ghost();
        werewolf.setName("Lupin");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        werewolf.setHauntFrom(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 4);
        werewolf.setHauntTo(cal.getTime());
        werewolf.setDescription("A human with the ability to shapeshift into a wolf.");
        lair = new House();
        lair.setName("The Lair");
        lair.setAddress("Mad Street 21");
        werewolf.setHouse(lair);

        assertThat(ghostDao.findById(werewolf.getId())).isNull();
        ghostDao.create(werewolf);
        assertThat(ghostDao.findById(werewolf.getId())).isNotNull();

        ghostDao.remove(werewolf); // put into the original state
    }

    @Test
    public void testUpdate() {
        zombie.setDescription("Zombies are most commonly found in horror and fantasy genre works.");
        ghostDao.update(zombie);

        List<Ghost> ghosts = ghostDao.findAll();
        Ghost returnedGhost = ghostDao.findByName(zombie.getName());

        assertThat(ghosts.size()).isEqualTo(2);
        assertThat(returnedGhost.getDescription()).isEqualTo(zombie.getDescription());
    }

    @Test
    public void testRemove() {
        assertThat(ghostDao.findById(zombie.getId())).isNotNull();
        ghostDao.remove(zombie);
        assertThat(ghostDao.findById(zombie.getId())).isNull();
        ghostDao.create(zombie); // put into original state
    }

    @Test
    public void testFindByID() {
        Ghost foundGhost = ghostDao.findById(witch.getId());

        assertThat(foundGhost.getName()).isEqualTo("Priscilla");
        assertThat(foundGhost.getDescription()).isEqualTo("A woman practicing black witchcraft with the aid of a devil.");
    }

    @Test
    public void testFindByName() {
        assertThat(ghostDao.findByName("Larry")).isEqualTo(zombie);
        assertThat(ghostDao.findByName("Priscilla")).isEqualTo(witch);
    }

    @Test
    public void testFindAll() {
        List<Ghost> ghosts = ghostDao.findAll();

        assertThat(ghosts).hasSize(2).contains(zombie, witch).doesNotContain(werewolf);
    }

}
