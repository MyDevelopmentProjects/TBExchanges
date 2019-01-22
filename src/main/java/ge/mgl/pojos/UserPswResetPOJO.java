package ge.mgl.pojos;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by user on 12/7/17.
 */
public class UserPswResetPOJO {

    @NotEmpty
    private String code;

    private String captcha;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
