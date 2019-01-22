package ge.mgl.pojos;

import ge.mgl.security.SpringSecurityUser;

/**
 * Created by user on 11/24/17.
 */
public class AuthWrapperPOJO {

    private boolean success = false;
    private int code = 200;
    private SpringSecurityUser user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SpringSecurityUser getUser() {
        return user;
    }

    public void setUser(SpringSecurityUser user) {
        this.user = user;
    }
}
