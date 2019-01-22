package ge.mgl.security;

import ge.mgl.entities.FUser;
import ge.mgl.service.FUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
/*import org.springframework.security.core.session.SessionRegistry;*/
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 3/16/17.
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FUserService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FUser user = this.usersService.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No GUser found with username '%s'.", username));
        } else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            grantedAuthorities.addAll(user.getRole().getPermissions().stream().map(
                    permission -> new SimpleGrantedAuthority(permission.getName())
            ).collect(Collectors.toList()));

            // Load GUser Role
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

            return new SpringSecurityUser(user,grantedAuthorities);
        }

    }
}
