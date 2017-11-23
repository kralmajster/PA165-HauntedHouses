package fi.muni.pa165.hauntedhouses.dto;

/**
 * @author Klara Kufova, 410091
 */

public class PersonAuthenticateDTO {
    
    private String login;
    private String password;
    
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

}
