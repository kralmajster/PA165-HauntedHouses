package fi.muni.pa165.hauntedhouses.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Klara Kufova, 410091
 */

public class HouseCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Long ownerID;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((name == null) ? 0 : name.hashCode())
                + ((address == null) ? 0 : address.hashCode())
                + ((ownerID == null) ? 0 : ownerID.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof HouseCreateDTO)) {
            return false;
        }

        HouseCreateDTO other = (HouseCreateDTO) object;

        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) {
            return false;
        }
        if (getAddress() != null ? !getAddress().equals(other.getAddress()) : other.getAddress() != null) {
            return false;
        }

        return getOwnerID() != null ? getOwnerID().equals(other.getOwnerID()) : other.getOwnerID() == null;
    }

}
