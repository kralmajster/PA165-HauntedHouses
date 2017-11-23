package fi.muni.pa165.hauntedhouses.dto;

import java.util.Date;
import java.util.Set;

/**
 * @author Klara Kufova, 410091
 */

public class HouseDTO {

    private Long id;
    private String name;
    private String address;
    private Date becameHauntedDate;
    private String history;
    private Long ownerID;
    private Set<GhostDTO> ghosts;
    private Set<PersonDTO> residents;

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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBecameHauntedDate(Date becameHauntedDate) {
        this.becameHauntedDate = becameHauntedDate;
    }

    public Date getBecameHauntedDate() {
        return becameHauntedDate;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getHistory() {
        return history;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setGhosts(Set<GhostDTO> ghosts) {
        this.ghosts = ghosts;
    }

    public Set<GhostDTO> getGhosts() {
        return ghosts;
    }

    public void setResidents(Set<PersonDTO> residents) {
        this.residents = residents;
    }

    public Set<PersonDTO> getResidents() {
        return residents;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        return prime + ((name == null) ? 0 : name.hashCode()) + ((address == null) ? 0 : address.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof HouseDTO)) return false;

        HouseDTO other = (HouseDTO) object;

        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;

        return getAddress() != null ? getAddress().equals(other.getAddress()) : other.getAddress() == null;
    }

    @Override
    public String toString() {
        return "HouseDTO {"
                + "ID: " + id
                + ", name: " + name
                + ", address: " + address
                + ", date of becoming haunted: " + becameHauntedDate
                + ", history: " + history
                + ", owner ID: " + ownerID
                + '}';
    }

}
