package fi.muni.pa165.hauntedhouses.data;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import fi.muni.pa165.hauntedhouses.enums.Role;
import fi.muni.pa165.hauntedhouses.service.AbilityService;
import fi.muni.pa165.hauntedhouses.service.GhostService;
import fi.muni.pa165.hauntedhouses.service.HouseService;
import fi.muni.pa165.hauntedhouses.service.PersonService;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Klara Kufova, 410091
 */

@Component
@Transactional
public class InitializerImpl implements Initializer {

    @Inject
    private AbilityService abilityService;

    @Inject
    private GhostService ghostService;

    @Inject
    private HouseService houseService;

    @Inject
    private PersonService personService;

    @Override
    public void loadData() {
        createAbilities();
        createHouses();
        createGhosts();
        createPeople();
    }
    
    private void createAbilities() {
        createAbility("burning blood", AbilityType.FIRE, Collections.EMPTY_SET);
        createAbility("confusion", AbilityType.CHASE, Collections.EMPTY_SET);
        createAbility("shadows", AbilityType.DARKNESS, Collections.EMPTY_SET);
    }
    
    private void createAbility(String name, AbilityType type, Set<Ghost> ghosts) {
        Ability ability = new Ability();
        
        ability.setName(name);
        ability.setType(type);
        ability.setGhosts(ghosts);
        
        abilityService.createAbility(ability);
    }
    
    private void createHouses() {
        createHouse("The Hovel", "Bloodcurdling 666");
        createHouse("The Abandoned Factory", "Nightmarish 13");
        createHouse("The Lair", "Mad Street 21");
    }
    
    private void createHouse(String name, String address) {
        House house = new House();
        
        house.setName(name);
        house.setAddress(address);
        
        houseService.createHouse(house);
    }
    
    private void createGhosts() {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        
        House hovel = houseService.findByName("The Hovel");
        House abandonedFactory = houseService.findByName("The Abandoned Factory");
        House lair = houseService.findByName("The Lair");
        
        Set<Ability> zombieAbilities = new HashSet();
        zombieAbilities.add(abilityService.findByName("confusion"));
        from.set(Calendar.HOUR_OF_DAY, 3);
        to.set(Calendar.HOUR_OF_DAY, 13);
        createGhost("zombie", from.getTime(), to.getTime(), abandonedFactory, zombieAbilities);
        
        Set<Ability> witchAbilities = new HashSet();
        witchAbilities.add(abilityService.findByName("burning blood"));
        witchAbilities.add(abilityService.findByName("shadows"));
        from.set(Calendar.HOUR_OF_DAY, 8);
        to.set(Calendar.HOUR_OF_DAY, 22);
        createGhost("witch", from.getTime(), to.getTime(), hovel, witchAbilities);
        
        from.set(Calendar.HOUR_OF_DAY, 0);
        to.set(Calendar.HOUR_OF_DAY, 4);
        createGhost("werewolf", from.getTime(), to.getTime(), lair, Collections.EMPTY_SET);
        
        Set<Ability> vampireAbilities = new HashSet();
        vampireAbilities.add(abilityService.findByName("shadows"));
        from.set(Calendar.HOUR_OF_DAY, 22);
        to.set(Calendar.HOUR_OF_DAY, 4);
        createGhost("vampire", from.getTime(), to.getTime(), abandonedFactory, vampireAbilities);
    }
    
    private void createGhost(String name, Date hauntFrom, Date hauntTo, House house, Set<Ability> abilities) {
        Ghost ghost = new Ghost();
        
        ghost.setName(name);
        ghost.setHauntFrom(hauntFrom);
        ghost.setHauntTo(hauntTo);
        ghost.setHouse(house);
        ghost.setAbilities(abilities);
        
        ghostService.createGhost(ghost);
    }
    
    private void createPeople() {
        createAdmin("Bruce Wayne", "admin", "admin");
        
        House hovel = houseService.findByName("The Hovel");
        House abandonedFactory = houseService.findByName("The Abandoned Factory");
        House lair = houseService.findByName("The Lair");
        
        createOwner("Alfred Pennyworth", "apennyworth", "owner", hovel);
        createOwner("James Gordon", "jgordon", "owner", abandonedFactory);
        
        createTenant("Damian Wayne", "dwayne", "tenant", hovel);
        createTenant("Selina Kyle", "skyle", "tenant", abandonedFactory);
        createTenant("Kate Kane", "kkane", "tenant", lair);
    }
    
    private void createAdmin(String name, String login, String password) {
        Person admin = new Person();
        
        admin.setName(name);
        admin.setLogin(login);
        admin.setType(Role.ADMIN);
        
        personService.registerPerson(admin, password);
    }
    
    private void createOwner(String name, String login, String password, House house) {
        Person owner = new Person();
        
        owner.setName(name);
        owner.setLogin(login);
        owner.setType(Role.OWNER);
        owner.setHouse(house);
        
        personService.registerPerson(owner, password);
    }
    
    private void createTenant(String name, String login, String password, House house) {
        Person tenant = new Person();
        
        tenant.setName(name);
        tenant.setLogin(login);
        tenant.setType(Role.RESIDENT);
        tenant.setHouse(house);
        
        personService.registerPerson(tenant, password);
    }
    
}
