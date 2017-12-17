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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
        String descriptionBlood = "This ability causes feelings similar to blood burning.";
        createAbility("Blood burning", descriptionBlood, AbilityType.FIRE);
        
        String descriptionConfusion = "The victim loses sense of the current situation.";
        createAbility("Confusion", descriptionConfusion, AbilityType.CHASE);
        
        String descriptionShadows = "This castes shadows into the surroundings.";
        createAbility("Shadows", descriptionShadows, AbilityType.DARKNESS);
    }
    
    private void createAbility(String name, String description, AbilityType type) {
        Ability ability = new Ability();
        
        ability.setName(name);
        ability.setDescription(description);
        ability.setType(type);
        
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
        zombieAbilities.add(abilityService.findByName("Confusion"));
        
        from.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH), 3, 0);
        to.set(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH), 13, 0);
        String zombieDescription = "An undead created through the reanimation of a human corpse.";
        createGhost("Zombie", from.getTime(), to.getTime(), zombieDescription, abandonedFactory, zombieAbilities);
        
        Set<Ability> witchAbilities = new HashSet();
        witchAbilities.add(abilityService.findByName("Blood burning"));
        witchAbilities.add(abilityService.findByName("Shadows"));
        from.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH), 8, 0);
        to.set(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH), 22, 0);
        String witchDescription = "A woman practicing black witchcraft with the aid of a devil.";
        createGhost("Witch", from.getTime(), to.getTime(), witchDescription, hovel, witchAbilities);
        
        from.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH), 0, 0);
        to.set(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH), 5, 0);
        String werewolfDescription = "A human with the ability to shapeshift into a wolf.";
        createGhost("Werewolf", from.getTime(), to.getTime(), werewolfDescription, lair, Collections.EMPTY_SET);
        
        Set<Ability> vampireAbilities = new HashSet();
        vampireAbilities.add(abilityService.findByName("Shadows"));
        from.set(from.get(Calendar.YEAR), from.get(Calendar.MONTH), from.get(Calendar.DAY_OF_MONTH), 22, 0);
        to.set(to.get(Calendar.YEAR), to.get(Calendar.MONTH), to.get(Calendar.DAY_OF_MONTH), 4, 0);
        String vampireDescription = "A creature that subsists by feeding on the life essence (generally in the form of blood) of the living.";
        createGhost("Vampire", from.getTime(), to.getTime(), vampireDescription, abandonedFactory, vampireAbilities);
    }
    
    private void createGhost(String name, Date hauntFrom, Date hauntTo, String description, House house, Set<Ability> abilities) {
        Ghost ghost = new Ghost();
        
        ghost.setName(name);
        ghost.setHauntFrom(hauntFrom);
        ghost.setHauntTo(hauntTo);
        ghost.setDescription(description);
        ghost.setHouse(house);
        ghost.setAbilities(abilities);
        
        ghostService.createGhost(ghost);
    }
    
    private void createPeople() {
        createAdmin("Bruce Wayne", "admin", "admin");
        
        House hovel = houseService.findByName("The Hovel");
        House abandonedFactory = houseService.findByName("The Abandoned Factory");
        House lair = houseService.findByName("The Lair");
        
        House hovelID = houseService.findById(hovel.getId());
        House abandonedFactoryID = houseService.findById(abandonedFactory.getId());
        House lairID = houseService.findById(lair.getId());
        
        List<House> alfredOwns = new ArrayList<>();
        alfredOwns.add(hovelID);
        alfredOwns.add(abandonedFactoryID);
        createOwner("Alfred Pennyworth", "apennyworth", "owner", alfredOwns);
        
        List<House> jamesOwns = new ArrayList<>();
        jamesOwns.add(lairID);
        createOwner("James Gordon", "jgordon", "owner", jamesOwns);
        
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
    
    private void createOwner(String name, String login, String password, List<House> ownsHouses) {
        Person owner = new Person();
        
        owner.setName(name);
        owner.setLogin(login);
        owner.setType(Role.OWNER);
        
        personService.registerPerson(owner, password);
        
        ownsHouses.stream().forEach((house) -> {
            house.setOwnerID(owner.getId());
            houseService.updateHouse(house);
        });
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
