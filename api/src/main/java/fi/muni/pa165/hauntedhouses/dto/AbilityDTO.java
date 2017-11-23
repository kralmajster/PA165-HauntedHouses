package fi.muni.pa165.hauntedhouses.dto;

import fi.muni.pa165.hauntedhouses.enums.AbilityType;

import java.util.Set;

/**
 * @author Klara Kufova, 410091
 */

public class AbilityDTO {

    private Long id;
    private String name;
    private String description;
    private AbilityType type;
    private Set<GhostDTO> ghosts;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAbilityType(AbilityType type) {
        this.type = type;
    }

    public AbilityType getAbilityType() {
        return type;
    }

    public void setGhosts(Set<GhostDTO> ghosts) {
        this.ghosts = ghosts;
    }

    public Set<GhostDTO> getGhosts() {
        return ghosts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((name == null) ? 0 : name.hashCode()) + ((type == null) ? 0 : type.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof AbilityDTO)) return false;

        AbilityDTO other = (AbilityDTO) object;

        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;
        
        return getAbilityType() != null ? getAbilityType().equals(other.getAbilityType()) : other.getAbilityType() == null;
    }

    @Override
    public String toString() {
        return "AbilityDTO {"
                + "ID: " + id
                + ", name: " + name
                + ", description: " + description
                + ", type: " + type
                + '}';
    }

}
