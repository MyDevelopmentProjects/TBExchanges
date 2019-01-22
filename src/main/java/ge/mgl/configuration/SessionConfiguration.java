package ge.mgl.configuration;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by MJaniko on 5/21/2017.
 */
@Configuration
public class SessionConfiguration {

    private class SessionListener implements  HttpSessionListener{

        @Override
        public void sessionCreated(HttpSessionEvent event) {
            System.out.println("==== Session is created ====");
            event.getSession().setMaxInactiveInterval(1800); // 30 minutes
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent event) {
            System.out.println("==== Session is destroyed ====");
        }

    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
        return new ServletListenerRegistrationBean<>(new SessionListener());
    }
}
