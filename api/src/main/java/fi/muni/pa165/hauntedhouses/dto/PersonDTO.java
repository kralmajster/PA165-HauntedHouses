package fi.muni.pa165.hauntedhouses.dto;

import fi.muni.pa165.hauntedhouses.enums.Role;

/**
 * @author Klara Kufova, 410091
 */

public class PersonDTO {

    private Long id;
    private String name;
    private String surname;
    private Role role;
    private String login;
    private String password;
    private HouseDTO house;

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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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
        return prime + ((login == null) ? 0 : login.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof PersonDTO)) return false;

        PersonDTO other = (PersonDTO) object;

        return getLogin() != null ? getLogin().equals(other.getLogin()) : other.getLogin() == null;
    }

    @Override
    public String toString() {
        return "PersonDTO {"
                + "ID: " + id
                + ", name: " + name
                + ", surname: " + surname
                + ", role: " + role
                + ", login: " + login
                + ", password: " + password
                + ", house: " + house
                + '}';
    }
}
