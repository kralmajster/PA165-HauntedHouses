package fi.muni.pa165.hauntedhouses.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Klara Kufova, 410091
 */

public class GhostChangeHouseDTO {
    
    private Long ghostID;
    
    @NotNull
    private HouseDTO newHouse;
    
    public void setGhostID(Long ghostID) {
        this.ghostID = ghostID;
    }

    public Long getGhostID() {
        return ghostID;
    }
    
    public void setNewHouse(HouseDTO newHouse) {
        this.newHouse = newHouse;
    }

    public HouseDTO getNewHouse() {
        return newHouse;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((ghostID == null) ? 0 : ghostID.hashCode()) + ((newHouse == null) ? 0 : newHouse.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof GhostChangeHouseDTO)) return false;

        GhostChangeHouseDTO other = (GhostChangeHouseDTO) object;

        if (getGhostID() != null ? !getGhostID().equals(other.getGhostID()) : other.getGhostID() != null) return false;

        return getNewHouse() != null ? getNewHouse().equals(other.getNewHouse()) : other.getNewHouse() == null;
    }

    @Override
    public String toString() {
        return "GhostChangeHouseDTO {"
                + "ID: " + ghostID
                + ", new house: " + newHouse
                + '}';
    }

}
