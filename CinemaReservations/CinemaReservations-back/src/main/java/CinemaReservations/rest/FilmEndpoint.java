package CinemaReservations.rest;

import CinemaReservations.model.Film;
import CinemaReservations.repository.FilmRepository;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.List;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/films")
public class FilmEndpoint {

    @Inject
    private FilmRepository filmRepository;

    public FilmEndpoint(){
    }

    @Inject
    public FilmEndpoint(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public String getDesc(){
        return "Hello world";
    }

    //@GET
    //@Produces(APPLICATION_JSON)
    public Response getFilms() {
        List<Film> films = filmRepository.findAll();
        if(films.size() == 0)
            return Response.noContent().build();
        return Response.ok(films).build();
    }

}
