package assigment.products.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assigment.products.service.ProductService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")

public class ProductResource {

	@Autowired
	ProductService productService;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/products/{id}")
	public Response updateProduct(@PathParam("id") Long id, String productString) throws Exception {
		try {
			productService.updateProduct(id, productString);
			return Response.ok().entity("Product with id: " + id + " successfully updated.").build();
		} catch (NotFoundException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/products")
	public Response createProduct(String productString) throws Exception {
		try {
			productService.addProduct(productString);
			return Response.ok().build();
		} catch (NotFoundException e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/products")
	public Response getAllProducts() throws Exception {
		String products = productService.getAll();
		return Response.ok().entity(products).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/products/{id}")
	public Response getProductById(@PathParam("id") Long id) throws Exception {
		try {
			String product = productService.getOne(id);
			return Response.ok().entity(product).build();
		} catch (NotFoundException e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
}