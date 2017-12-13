package fi.muni.pa165.hauntedhouses.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Klara Kufova, 410091
 * @author Mario Majernik (422165)
 */

@JsonIgnoreProperties({ "ghosts", "residents" })
public abstract class HouseDTOMixin {
    
}
