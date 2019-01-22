package ge.mgl;

import ge.mgl.configuration.StorageConfiguration;
import ge.mgl.service.StorageService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.ServletException;

@EnableConfigurationProperties(StorageConfiguration.class)
@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private StorageService serv;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }


    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
                Context root = null;
                try {
                    root = tomcat.addWebapp("/uploads", serv.getRootLocation().toAbsolutePath().toString());
                    root.setAllowCasualMultipartParsing(true);
                } catch (ServletException e) {
                    //e.printStackTrace();
                }
                return super.getTomcatEmbeddedServletContainer(tomcat);
            }
        };
    }

}