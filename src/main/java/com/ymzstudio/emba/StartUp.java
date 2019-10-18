package com.ymzstudio.emba;

import com.ymzstudio.emba.config.ApplicationConstants;
import com.ymzstudio.emba.config.ApplicationProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class StartUp implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(StartUp.class);

    private final Environment env;

    @Autowired
    public StartUp(Environment env, ApplicationProperties applicationProperties) {
        this.env = env;
        log.info("written by: {}", applicationProperties.getAuthor());
    }

    /**
     * Main method, used to run the application.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StartUp.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    /**
     * Initializes tradeSystem.
     * <p>
     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
     * </p>
     */
    @Override
    public void afterPropertiesSet() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(ApplicationConstants.Profile.DEVELOPMENT) &&
            activeProfiles.contains(ApplicationConstants.Profile.PRODUCTION)) {
            log.error(
                "Application should not run with both the 'dev' and 'prod' profiles at the same time."
            );
            throw new Error("Application should not run with both the 'dev' and 'prod' profiles at the same time.");
        }
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}{}\n\t" +
                        "External: \t{}://{}:{}{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                 env.getProperty("spring.application.name"),
                 protocol,
                 serverPort,
                 contextPath,
                 protocol,
                 hostAddress,
                 serverPort,
                 contextPath,
                 env.getActiveProfiles());
    }

}
