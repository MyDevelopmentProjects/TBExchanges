package ge.mgl.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ge.mgl.pojos.UserRegistrationPOJO;
import ge.mgl.utils.MGLStringUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user")
public class FUser extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private FRole role;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "fullname")
    private String fullName;

    public FUser() {
    }

    public FUser(UserRegistrationPOJO user) {
        FRole role = new FRole();
        role.setId(1L);
        this.setRole(role);
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setFullName(user.getFirstName() + " " + user.getLastName());
    }

    public FUser(FUser user) {
        this.id = user.id;
        this.role = user.role;
        this.username = user.username;
        this.password = user.password;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.dateCreated = user.dateCreated;
        this.dateUpdated = user.dateUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FRole getRole() {
        return role;
    }

    public void setRole(FRole role) {
        this.role = role;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
