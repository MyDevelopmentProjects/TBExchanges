package ge.mgl.handler;

import ge.mgl.utils.RequestResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MJaniko on 11/20/2016.
 */
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().print(
                new RequestResponse(false, "access_is_denied", 401)
        );

    }
}
