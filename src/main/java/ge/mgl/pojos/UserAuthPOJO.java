package ge.mgl.pojos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Mikheil on 11/15/2017.
 */
public class UserAuthPOJO {

    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 4, max = 64)
    private String password;

    private boolean remember;

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

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
