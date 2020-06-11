package CinemaReservations.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        System.out.println("Dupa dupa patrz tutaj");
        System.out.println(e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
