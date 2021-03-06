package sb.mep.resources;

import io.dropwizard.jersey.params.LongParam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sb.mep.api.DataResponse;
import sb.mep.api.Dish;
import sb.mep.dao.DishDao;

@Path("/api/dish")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DishResource extends BaseResouce {
	
	private DishDao dao;
	
	public DishResource(DishDao dao) {
		this.dao = dao;
	}
	
	@GET
	public Response fetch() {
		List<Dish> dishes = dao.findAll();
		return Response.ok(DataResponse.build(dishes)).build();
	}
	
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") LongParam id) {
		Dish dish = dao.findById(id.get()).get();
		return Response.ok(DataResponse.build(dish)).build();
	}
	
	@POST
	public Response save(Dish dish) {
		System.out.println(dish);
		System.out.println(dish.getEvent());
		System.out.println(dish.getKind());
		dish = dao.create(dish);
		return Response.ok(DataResponse.build(dish)).build();
	}
	
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") LongParam id) {
		Dish dish = dao.findById(id.get()).get();
		dish = dao.update(dish);
		return Response.ok(DataResponse.build(dish)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") LongParam id) {
		Dish dish = dao.findById(id.get()).get();
		dao.delete(dish);
		return fetch();
	}

}
