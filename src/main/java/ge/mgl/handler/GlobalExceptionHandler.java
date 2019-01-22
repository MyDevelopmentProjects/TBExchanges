package ge.mgl.handler;

import ge.mgl.utils.RequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

import static ge.mgl.utils.constants.Constants.ErrorCodes.ErrorResponse.*;

/**
 * Created by MJaniko on 3/12/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {Exception.class, UsernameNotFoundException.class, AuthenticationException.class, RuntimeException.class, ServletException.class})
    public ResponseEntity<?> unCaughtErrorHandler(Exception e) {
        //return "redirect:/";
        return this.generateErrorReport(e);
    }

    private ResponseEntity<?> generateErrorReport(Exception e) {
        RequestResponse response = new RequestResponse();
        response.setSuccess(false);
        switch (e.getClass().getCanonicalName()) {
            case "javax.persistence.PersistenceException":
                String message = e.getMessage();
                if (message != null && message.contains("ConstraintViolationException")) {
                    response.setMessage(DUPLICATE_RECORD);
                }
                else {
                    response.setMessage(PERSISTENCE_EXCEPTION);
                }
                break;
            case "org.springframework.dao.DataIntegrityViolationException":
                response.setMessage(RECORD_IS_USED_IN_OTHER_TABLES);
                break;
            case "org.springframework.security.access.AccessDeniedException":
            case "org.springframework.security.authentication.AuthenticationCredentialsNotFoundException":
                response.setMessage(ACCESS_IS_DENIED);
                break;
            case "org.springframework.security.authentication.BadCredentialsException":
                response.setMessage(messageSource.getMessage("auth.modal.badcredentials", null, LocaleContextHolder.getLocale()));
                break;
            case "org.springframework.security.authentication.LockedException":
                String msg = messageSource.getMessage("auth.user.locked", null, LocaleContextHolder.getLocale());
                response.setMessage(msg);
                break;
            case "org.springframework.mail.MailSendException":
            case "org.springframework.mail.MessagingException":
            case "org.springframework.web.HttpRequestMethodNotSupportedException":
            case "org.springframework.web.bind.MethodArgumentNotValidException":
                response.setMessage(e.getMessage());
                break;
            default:
                logger.error("Found Exception", e);
                response.setMessage(UNKNOWN);
                break;
        }
        /*String stack = ExceptionUtils.getStackTrace(e);
        response.setExceptionMessage(stack);*/
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}