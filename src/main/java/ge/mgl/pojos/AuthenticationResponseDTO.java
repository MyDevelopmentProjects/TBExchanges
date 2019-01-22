package ge.mgl.pojos;

/**
 * Created by user on 11/6/17.
 */
public class AuthenticationResponseDTO {

    private String token;

    public AuthenticationResponseDTO() {
        super();
    }

    public AuthenticationResponseDTO(String token) {
        this.setToken(token);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
