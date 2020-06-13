package CinemaReservations.rest;

import CinemaReservations.model.Film;
import CinemaReservations.model.User;
import CinemaReservations.repository.UserRepository;
import CinemaReservations.rest.auth.Auth;
import CinemaReservations.rest.data.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
public class UserEndpoint {

    @Inject
    private UserRepository userRepository;

    @GET
    //@Auth
    @Produces(APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = userRepository.findAll();
        if(users.size() == 0)
            return Response.noContent().build();
        return Response.ok(users).build();
    }


    @POST
    @Consumes(APPLICATION_JSON)
    public Response register(UserDTO user) {
        userRepository.create(new User(user.getUsername(), user.getPassword()));
        return Response.noContent().build();
    }

}
