package CinemaReservations.rest;

import CinemaReservations.model.MovieShow;
import CinemaReservations.repository.MovieShowRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/shows")
public class MovieShowEndpoint {

    @Inject
    private MovieShowRepository movieShowRepository;

    public MovieShowEndpoint(){
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{id : \\d+}")
    public Response getMovieShow(@PathParam("id") @Min(1) Long id) {
        MovieShow movieShow = movieShowRepository.find(id);
        if (movieShow==null)
            return Response.noContent().build();
        return Response.ok(movieShow).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createMovieShow(MovieShow movieShow, @Context UriInfo uriInfo) {
        movieShow = movieShowRepository.create(movieShow);
        URI createdURI = uriInfo.getBaseUriBuilder().path(movieShow.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    @DELETE
    @Consumes(APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteMovieShow(@PathParam("id") Long id) {
        movieShowRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getMovieShows() {
        List<MovieShow> movieShows = movieShowRepository.findAll();
        if(movieShows.size() == 0)
            return Response.noContent().build();
        return Response.ok(movieShows).build();
    }

    @GET
    @Path("/count")
    public Response countMovieShows() {
        Long nbOfMovieShows = movieShowRepository.countAll();
        if(nbOfMovieShows==0)
            return Response.noContent().build();
        return Response.ok(nbOfMovieShows.toString()).build();
    }

}
