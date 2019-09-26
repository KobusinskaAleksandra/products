package assigment.products.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/heartbeat")
public class HeartbeatResource {

    @GET
    public String heartbeat() throws Exception {
        return "ok";
    }
}
