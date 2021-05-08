package com.duzceuniversity.kurumtakip;

import com.duzceuniversity.kurumtakip.Service.KurumTakipAuditorAware;
import com.duzceuniversity.kurumtakip.Service.StaffService;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan("com.duzceuniversity.kurumtakip.DataBase.Repository")
//@EntityScan("com.duzceuniversity.kurumtakip")
//@EnableJpaRepositories("com.duzceuniversity.kurumtakip.DataBase.Repository")
public class KurumTakipApplication extends SpringBootServletInitializer implements ApplicationRunner {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(KurumTakipApplication.class, args);
        Runtime rt = Runtime.getRuntime();
        String url = "http://localhost:8080";
        rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        Logger logger = LoggerFactory.getLogger(KurumTakipApplication.class);
        logger.info("System is running................................");
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new KurumTakipAuditorAware();
    }

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
