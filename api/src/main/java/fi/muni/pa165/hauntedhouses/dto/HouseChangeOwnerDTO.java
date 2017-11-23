package fi.muni.pa165.hauntedhouses.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Klara Kufova, 410091
 */

public class HouseChangeOwnerDTO {
    
    private Long houseID;
    
    @NotNull
    private Long newOwnerID;
    
    public void setHouseID(Long houseID) {
        this.houseID = houseID;
    }

    public Long getHouseID() {
        return houseID;
    }
    
    public void setNewOwnerID(Long newOwnerID) {
        this.newOwnerID = newOwnerID;
    }

    public Long getNewOwnerID() {
        return newOwnerID;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        return prime + ((houseID == null) ? 0 : houseID.hashCode()) + ((newOwnerID == null) ? 0 : newOwnerID.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof HouseChangeOwnerDTO)) return false;

        HouseChangeOwnerDTO other = (HouseChangeOwnerDTO) object;

        if (getHouseID() != null ? !getHouseID().equals(other.getHouseID()) : other.getHouseID() != null) return false;

        return getNewOwnerID() != null ? getNewOwnerID().equals(other.getNewOwnerID()) : other.getNewOwnerID() == null;
    }

    @Override
    public String toString() {
        return "HouseChangeOwnerDTO {"
                + "ID: " + houseID
                + ", new owner ID: " + newOwnerID
                + '}';
    }
}
