package ge.mgl.pojos;

import ge.mgl.security.SpringSecurityUser;

/**
 * Created by user on 11/16/17.
 */
public class UserSuccessPOJO {

    private String token;
    private SpringSecurityUser user;

    public UserSuccessPOJO(String token, SpringSecurityUser user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SpringSecurityUser getUser() {
        return user;
    }

    public void SpringSecurityUser(SpringSecurityUser user) {
        this.user = user;
    }
}
