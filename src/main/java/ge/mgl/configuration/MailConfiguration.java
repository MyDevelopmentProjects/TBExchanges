package ge.mgl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by MJaniko on 3/29/2017.
 */
@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("host437.hostmonster.com");
        mailSender.setUsername("reporter@cleantech.ge");
        mailSender.setPassword("321147Qa@");
        mailSender.setPort(465);
        mailSender.setProtocol("smtps");

        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.starttls.enable", "false");
        mailProps.put("mail.smtps.auth", "true");
        mailProps.put("mail.smtp.ssl.enable", "false");
        mailProps.put("mail.transport.protocol", "smtps");
        mailProps.put("mail.debug", "true"); // For Debug Purpose
        mailProps.put("mail.mime.charset", "utf8");

        mailSender.setJavaMailProperties(mailProps);

        return mailSender;
    }

}
