package CinemaReservations.rest;

import CinemaReservations.repository.ReservationRepository;
import CinemaReservations.model.Reservation;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/reservations")
public class ReservationEndpoint {

    @Inject
    private ReservationRepository reservationRepository;

    public ReservationEndpoint(){
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{id : \\d+}")
    public Response getReservation(@PathParam("id") @Min(1) Long id) {
        Reservation reservation = reservationRepository.find(id);
        if (reservation==null)
            return Response.noContent().build();
        return Response.ok(reservation).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createReservation(Reservation reservation, @Context UriInfo uriInfo) {
        try{
            reservation = reservationRepository.create(reservation);
            URI createdURI = uriInfo.getBaseUriBuilder().path(reservation.getId().toString()).build();
            return Response.created(createdURI).build();
        } catch (Exception e){
            e.getMessage();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Consumes(APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteReservation(@PathParam("id") Long id) {
        reservationRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        if(reservations.size() == 0)
            return Response.noContent().build();
        return Response.ok(reservations).build();
    }

    @GET
    @Path("/count")
    public Response countReservations() {
        Long nbOfReservations = reservationRepository.countAll();
        if(nbOfReservations==0)
            return Response.noContent().build();
        return Response.ok(nbOfReservations.toString()).build();
    }
}
