package sb.mep.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/preparation")
@Produces("application/json; charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class PreparationResource extends BaseResouce {

}