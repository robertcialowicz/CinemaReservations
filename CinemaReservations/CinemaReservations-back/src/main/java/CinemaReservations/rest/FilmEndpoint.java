package CinemaReservations.rest;

import CinemaReservations.model.Film;
import CinemaReservations.repository.FilmRepository;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/films")
public class FilmEndpoint {

    @Inject
    private FilmRepository filmRepository;

    public FilmEndpoint(){
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getFilms() {
        List<Film> films = filmRepository.findAll();
        if(films.size() == 0)
            return Response.noContent().build();
        return Response.ok(films).build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{id : \\d+}")
    public Response getFilm(@PathParam("id") @Min(1) Long id) {
        Film film = filmRepository.find(id);
        if (film==null)
            return Response.noContent().build();
        return Response.ok(film).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createFilm(Film film, @Context UriInfo uriInfo) {
        film = filmRepository.create(film);
        URI createdURI = uriInfo.getBaseUriBuilder().path(film.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    @DELETE
    @Consumes(APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteFilm(@PathParam("id") Long id) {
        filmRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/count")
    public Response countFilms() {
        Long nbOfFilms = filmRepository.countAll();
        if(nbOfFilms==0)
            return Response.noContent().build();
        return Response.ok(nbOfFilms.toString()).build();
    }
}
