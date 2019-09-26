package assigment.products.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import assigment.products.rest.ProductResource;
import assigment.products.rest.HeartbeatResource;

import javax.ws.rs.ApplicationPath;

@Component
@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("assigment.products")
                .register(HeartbeatResource.class)
                .register(ProductResource.class);

    }

}