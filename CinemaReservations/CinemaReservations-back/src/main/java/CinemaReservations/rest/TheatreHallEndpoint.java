package CinemaReservations.rest;

import CinemaReservations.model.TheatreHall;
import CinemaReservations.repository.TheatreHallRepository;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/halls")
public class TheatreHallEndpoint {

    @Inject
    private TheatreHallRepository theatreHallRepository;

    public TheatreHallEndpoint(){
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{id : \\d+}")
    public Response getTheatreHall(@PathParam("id") @Min(1) Long id) {
        TheatreHall theatreHall = theatreHallRepository.find(id);
        if (theatreHall==null)
            return Response.noContent().build();
        return Response.ok(theatreHall).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createTheatreHall(TheatreHall theatreHall, @Context UriInfo uriInfo) {
        theatreHall = theatreHallRepository.create(theatreHall);
        URI createdURI = uriInfo.getBaseUriBuilder().path(theatreHall.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    @DELETE
    @Consumes(APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteTheatreHall(@PathParam("id") Long id) {
        theatreHallRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getTheatreHalls() {
        List<TheatreHall> theatreHalls = theatreHallRepository.findAll();
        if(theatreHalls.size() == 0)
            return Response.noContent().build();
        return Response.ok(theatreHalls).build();
    }

}
