package fi.muni.pa165.hauntedhouses.dto;

import java.util.Date;
import java.util.Set;

/**
 * @author Klara Kufova, 410091
 */

public class GhostDTO {

    private Long id;
    private String name;
    private Date hauntFrom;
    private Date hauntTo;
    private String description;
    private String hauntReason;
    private HouseDTO house;
    private Set<AbilityDTO> abilities;

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

    public void setHauntFrom(Date hauntFrom) {
        this.hauntFrom = hauntFrom;
    }

    public Date getHauntFrom() {
        return hauntFrom;
    }

    public void setHauntTo(Date hauntTo) {
        this.hauntTo = hauntTo;
    }

    public Date getHauntTo() {
        return hauntTo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setHauntReason(String hauntReason) {
        this.hauntReason = hauntReason;
    }

    public String getHauntReason() {
        return hauntReason;
    }

    public void setHouse(HouseDTO house) {
        this.house = house;
    }

    public HouseDTO getHouse() {
        return house;
    }

    public void setAbilities(Set<AbilityDTO> abilities) {
        this.abilities = abilities;
    }

    public Set<AbilityDTO> getAbilities() {
        return abilities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((name == null) ? 0 : name.hashCode())
                + ((hauntFrom == null) ? 0 : hauntFrom.hashCode())
                + ((hauntTo == null) ? 0 : hauntTo.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof GhostDTO)) return false;

        GhostDTO other = (GhostDTO) object;

        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;
        if (getHauntFrom() != null ? !getHauntFrom().equals(other.getHauntFrom()) : other.getHauntFrom() != null) return false;

        return getHauntTo() != null ? getHauntTo().equals(other.getHauntTo()) : other.getHauntTo() == null;
    }

    @Override
    public String toString() {
        return "GhostDTO {"
                + "ID: " + id
                + ", name: " + name
                + ", haunts from: " + hauntFrom
                + ", haunts to: " + hauntTo
                + ", description: " + description
                + ", reason for haunting: " + hauntReason
                + ", house: " + house
                + '}';
    }

}
