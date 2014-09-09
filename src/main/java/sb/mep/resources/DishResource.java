package sb.mep.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/dish")
@Produces("application/json; charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class DishResource extends BaseResouce {
	
	@GET
	@Path("fetch")
	public Response fetch() {
		return null;
	}
	
	@GET
	@Path("get")
	public Response get() {
		return null;
	}
	
	@POST
	public Response save() {
		return null;
	}

}
