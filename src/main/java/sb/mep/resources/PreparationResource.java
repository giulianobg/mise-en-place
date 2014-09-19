package sb.mep.resources;

import io.dropwizard.jersey.params.LongParam;

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
import javax.ws.rs.core.Response.Status;

import sb.mep.api.DataResponse;
import sb.mep.api.Preparation;
import sb.mep.dao.PreparationDao;

@Path("/api/preparation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PreparationResource extends BaseResouce {

	private PreparationDao dao;

	public PreparationResource(PreparationDao dao) {
		this.dao = dao;
	}
	
//	@GET
//	public Response fetch() {
//		List<Preparation> preparations = dao.findAll();
//		return Response.ok(DataResponse.build(preparations)).build();
//	}
	
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") LongParam id) {
		Preparation prep = dao.findById(id.get()).get();
		return Response.ok(DataResponse.build(prep)).build();
	}
	
	@POST
	public Response save(Preparation prep) {
		prep = dao.create(prep);
		return Response.ok(DataResponse.build(prep)).build();
	}
	
	@PUT
	@Path("{id}")
	public Response update(Preparation prep) {
		prep = dao.update(prep);
		return Response.ok(DataResponse.build(prep)).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") LongParam id) {
		Preparation prep = dao.findById(id.get()).get();
		dao.delete(prep);
		return Response.ok(DataResponse.build(Status.OK)).build();
	}

}
