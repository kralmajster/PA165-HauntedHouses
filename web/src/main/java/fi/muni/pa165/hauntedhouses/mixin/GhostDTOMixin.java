package fi.muni.pa165.hauntedhouses.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Klara Kufova (410091)
 * @author Mario Majernik (422165)
 */

@JsonIgnoreProperties({ "abilities" })
public abstract class GhostDTOMixin {
    
}