package ge.mgl.security;

import ge.mgl.entities.FRole;
import ge.mgl.entities.FUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Mikheil on 3/17/17.
 */
public class SpringSecurityUser extends FUser implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public SpringSecurityUser(FUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user);
        this.setAuthorities(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public FRole getRole() {
        return super.getRole();
    }

}
