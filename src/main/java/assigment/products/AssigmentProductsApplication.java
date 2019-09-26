package assigment.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"assigment.products"})
@ComponentScan("assigment.products")
@PropertySource({"classpath:application_${spring.profiles.active}.properties","classpath:db_${spring.profiles.active}.properties"})

public class AssigmentProductsApplication extends SpringBootServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssigmentProductsApplication.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(AssigmentProductsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args ->
                LOGGER.info("Active profile: " + activeProfile);
    }
    
}
