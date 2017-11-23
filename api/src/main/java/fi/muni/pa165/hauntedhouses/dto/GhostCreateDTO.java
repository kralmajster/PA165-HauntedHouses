package fi.muni.pa165.hauntedhouses.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Klara Kufova, 410091
 */

public class GhostCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Date hauntFrom;

    @NotNull
    private Date hauntTo;

    @NotNull
    private HouseDTO house;

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

    public void setHouse(HouseDTO house) {
        this.house = house;
    }

    public HouseDTO getHouse() {
        return house;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((name == null) ? 0 : name.hashCode())
                + ((hauntFrom == null) ? 0 : hauntFrom.hashCode())
                + ((hauntTo == null) ? 0 : hauntTo.hashCode())
                + ((house == null) ? 0 : house.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof GhostCreateDTO)) return false;

        GhostCreateDTO other = (GhostCreateDTO) object;

        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;
        if (getHauntFrom() != null ? !getHauntFrom().equals(other.getHauntFrom()) : other.getHauntFrom() != null) return false;
        if (getHauntTo() != null ? !getHauntTo().equals(other.getHauntTo()) : other.getHauntTo() != null) return false;

        return getHouse() != null ? getHouse().equals(other.getHouse()) : other.getHouse() == null;
    }

}
