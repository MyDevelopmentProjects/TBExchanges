package ge.mgl.pojos;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Mikheil on 11/27/2017.
 */
public class UserProfileUpdatePOJO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String firstNameGE;
    @NotEmpty
    private String lastNameGE;
    @NotEmpty
    private String lastNameEN;
    @NotEmpty
    private String firstNameEN;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String address1;
    @NotEmpty
    private String address2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstNameGE() {
        return firstNameGE;
    }

    public void setFirstNameGE(String firstNameGE) {
        this.firstNameGE = firstNameGE;
    }

    public String getLastNameGE() {
        return lastNameGE;
    }

    public void setLastNameGE(String lastNameGE) {
        this.lastNameGE = lastNameGE;
    }

    public String getFirstNameEN() {
        return firstNameEN;
    }

    public void setFirstNameEN(String firstNameEN) {
        this.firstNameEN = firstNameEN;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getLastNameEN() {
        return lastNameEN;
    }

    public void setLastNameEN(String lastNameEN) {
        this.lastNameEN = lastNameEN;
    }
}
