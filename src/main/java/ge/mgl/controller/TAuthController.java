package ge.mgl.controller;

import ge.mgl.entities.FUser;
import ge.mgl.pojos.*;
import ge.mgl.security.SpringSecurityUser;
import ge.mgl.service.FUserService;
import ge.mgl.utils.RequestResponse;
import ge.mgl.utils.RequestResponseWithSource;
import ge.mgl.utils.TokenUtils;
import ge.mgl.utils.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class TAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private FUserService usersService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RequestResponseWithSource<UserAuthenticationPOJO> getMe() {

        RequestResponseWithSource<UserAuthenticationPOJO> result = new RequestResponseWithSource<>(true);

        Principal user = SecurityContextHolder.getContext().getAuthentication();
        if (user != null) {
            SpringSecurityUser profile = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(user.getName());
            if (profile != null) {
                result.setCode(200);
                result.setMessage("SUCCESSFULLY_AUTHORIZED");
                result.setSource(new UserAuthenticationPOJO.Builder()
                        .setSuccess(true)
                        .setUser(profile)
                        .setCode(200)
                        .build());
                return result;
            }
        }

        result.setSource(new UserAuthenticationPOJO.Builder()
                .setSuccess(false)
                .setMessage(Constants.ACCESS_IS_DENIED)
                .setCode(401)
                .build());
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RequestResponseWithSource<UserSuccessPOJO> authenticationRequest(@Valid @RequestBody UserAuthPOJO authenticationRequest) {

        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        SpringSecurityUser user = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = this.tokenUtils.generateToken(user);

        RequestResponseWithSource<UserSuccessPOJO> result = new RequestResponseWithSource<>(true);
        result.setSource(new UserSuccessPOJO(token, user));
        result.setCode(200);
        result.setMessage("SUCCESSFULLY_AUTHORIZED");
        // Return the token
        return result;
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        if (token == null) {
            return ResponseEntity.ok(Constants.EMPTY_OR_WRONG_TOKEN);
        }
        String username = this.tokenUtils.getUsernameFromToken(token);
        SpringSecurityUser user = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token)) {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponseDTO(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PAGE_USER')")
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResponseEntity<?> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
        }
        return ResponseEntity.ok(new RequestResponse(true));
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public RequestResponseWithSource<FUser> register(@Valid @RequestBody UserRegistrationPOJO user) {
        Principal usr = SecurityContextHolder.getContext().getAuthentication();
        if (usr != null) {
            return new RequestResponseWithSource<>(false, "Can not register user while logged in");
        }

        //WARNING: NO MORE NEEDED HERE TO CHECK OR VALIDATE USER WITH SMS CODE
//      if (!this.validateSMS(user.getPhone(), user.getSmsCode())) {
//          return new RequestResponseWithSource<>(false, "Verification Failed");
//      }

        return usersService.register(user);
    }
}
