package api.course.api.v1.rest;

import api.course.api.v1.impl.AuditService;
import api.course.api.v1.models.Event;
import api.course.utilities.constant.Constants;
import org.jboss.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/asan" + Constants.UriFragment.API_V1 + Constants.Endpoint.EVENTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuditResource {

    private static final Logger logger = Logger.getLogger(AuditResource.class.getName());

    @Inject private AuditService auditService;

    @GET
    @Path("/")
    public Response listEvents() {
        logger.info("listEvents endpoint hit");
        List<Event> eventList = auditService.listEvents();
        return Response.status(Response.Status.OK).entity(eventList).build();
    }
}
