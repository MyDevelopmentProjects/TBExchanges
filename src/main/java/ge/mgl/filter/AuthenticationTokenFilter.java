package ge.mgl.filter;

import ge.mgl.entities.FUser;
import ge.mgl.service.FUserService;
import ge.mgl.utils.TokenUtils;
import ge.mgl.utils.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by user on 11/6/17.
 */
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FUserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // First Clear Context
        SecurityContextHolder.clearContext();

        tokenUtils = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext())
                .getBean(TokenUtils.class);
        userDetailsService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext())
                .getBean(UserDetailsService.class);

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(Constants.TOKEN_HEADER);

        String username = this.tokenUtils.getUsernameFromToken(authToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            FUser user = userService.findByUserName(username);
            if(user != null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (userDetails != null && this.tokenUtils.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
