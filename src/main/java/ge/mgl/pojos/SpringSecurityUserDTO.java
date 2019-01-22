package ge.mgl.pojos;

/**
 * Created by Mikheil on 11/16/2017.
 */
public class SpringSecurityUserDTO {

    private String firstNameGE;
    private String firstNameEN;
    private String lastNameGE;
    private String lastNameEN;

    public String getFirstNameGE() {
        return firstNameGE;
    }

    public void setFirstNameGE(String firstNameGE) {
        this.firstNameGE = firstNameGE;
    }

    public String getFirstNameEN() {
        return firstNameEN;
    }

    public void setFirstNameEN(String firstNameEN) {
        this.firstNameEN = firstNameEN;
    }

    public String getLastNameGE() {
        return lastNameGE;
    }

    public void setLastNameGE(String lastNameGE) {
        this.lastNameGE = lastNameGE;
    }

    public String getLastNameEN() {
        return lastNameEN;
    }

    public void setLastNameEN(String lastNameEN) {
        this.lastNameEN = lastNameEN;
    }
}
