package ge.mgl.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by MJaniko on 4/12/2017.
 */
public class UserUtils {

    private static Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static SpringSecurityUser currentUser() {
        if(isAuthenticated()){
            return (SpringSecurityUser) getAuth().getPrincipal();
        }
        return null;
    }

    public static boolean isAuthenticated() {
       return (getAuth() != null && getAuth().isAuthenticated() && !(getAuth() instanceof AnonymousAuthenticationToken));
    }

    public static boolean isAdmin(){
        return hasRole("ADMIN");
    }

    public static boolean hasRole(String roleName){
        if(currentUser() != null && currentUser().getRole() != null){
            if(currentUser().getRole().getName().equals(roleName)){
                return true;
            }
        }
        return false;
    }

    public boolean hasAnyRole(String roleName){
        return false;
    }

}
