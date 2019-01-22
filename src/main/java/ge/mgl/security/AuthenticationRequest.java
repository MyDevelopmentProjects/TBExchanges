package ge.mgl.security;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by MJaniko on 3/25/2017.
 */
public class AuthenticationRequest {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String password;

    public AuthenticationRequest(){
        super();
    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}