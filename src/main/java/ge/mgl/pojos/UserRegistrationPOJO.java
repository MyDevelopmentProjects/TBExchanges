package ge.mgl.pojos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by user on 11/12/17.
 */
public class UserRegistrationPOJO {

    @NotEmpty
    @Size(min = 4, max = 15)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String imgUrl;

    @NotEmpty
    private String dob;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
