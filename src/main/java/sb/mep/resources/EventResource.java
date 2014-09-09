package sb.mep.resources;

import io.dropwizard.jersey.params.LongParam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sb.mep.api.DataResponse;
import sb.mep.api.Event;
import sb.mep.dao.EventDao;

@Path("/api/event")
@Produces("application/json; charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource extends BaseResouce {
	
	final EventDao dao;
	
	public EventResource(EventDao dao) {
		this.dao = dao;
	}
	
	@GET
	@Path("fetch")
	public Response fetch() {
		List<Event> events = dao.findAll();
		return Response.ok(DataResponse.build(events)).build();
	}
	
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") LongParam id) {
		System.out.println("EventResource.get()");
		Event event = dao.findById(id.get()).get();
		return Response.ok(DataResponse.build(event)).build();
	}
	
	@POST
	public Response save() {
		return null;
	}
	
	@PUT
	public Response update() {
		return null;
	}

}
