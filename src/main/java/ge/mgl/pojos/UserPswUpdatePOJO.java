package ge.mgl.pojos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by user on 11/27/17.
 */
public class UserPswUpdatePOJO {

    @NotEmpty
    @Size(min = 8, max = 64)
    private String currentPassword;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String newPassword;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String repeatPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
