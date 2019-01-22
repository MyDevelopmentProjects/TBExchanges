package ge.mgl.pojos;

import ge.mgl.security.SpringSecurityUser;

/**
 * Created by Mikheil on 11/25/2017.
 */
public class UserAuthenticationPOJO {

    private Boolean success = false;
    private String message;
    private Integer code;
    private SpringSecurityUser user;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SpringSecurityUser getUser() {
        return user;
    }

    public void setUser(SpringSecurityUser user) {
        this.user = user;
    }

    public static class Builder{
        private Boolean success = false;
        private String message;
        private Integer code;
        private SpringSecurityUser user;

        private Double balance;
        private Double credit;

        public Builder setSuccess(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setCode(Integer code) {
            this.code = code;
            return this;
        }

        public Builder setUser(SpringSecurityUser user) {
            this.user = user;
            return this;
        }

        public Builder setBalance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setCredit(Double credit) {
            this.credit = credit;
            return this;
        }

        public UserAuthenticationPOJO build(){
            UserAuthenticationPOJO response = new UserAuthenticationPOJO();
            response.setUser(this.user);
            response.setSuccess(this.success);
            response.setMessage(this.message);
            response.setCode(this.code);
            return response;
        }
    }
}
