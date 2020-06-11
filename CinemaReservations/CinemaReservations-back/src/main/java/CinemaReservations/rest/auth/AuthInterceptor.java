package CinemaReservations.rest.auth;

import CinemaReservations.model.User;
import CinemaReservations.repository.UserRepository;
import org.wildfly.security.auth.AuthenticationException;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

@Interceptor
@Auth
public class AuthInterceptor {

    @Inject
    HttpServletRequest request;

    @Inject
    UserRepository userRepository;

    @AroundInvoke
    public Object authenticate(InvocationContext ctx) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || authorizationHeader.trim().equals("")) {
            throw new AuthenticationException("Authorization header not found");
        }

        Credentials credentials = new Credentials(authorizationHeader);
        User user = userRepository.findByUsername(credentials.getUsername());

        if (user == null) {
            throw new AuthenticationException("User with username " + credentials.getUsername() + " doesnt exists");
        }

        if (!user.getPassword().equals(credentials.getPassword())) {
            throw  new AuthenticationException("User with username " + credentials.getUsername() + " tried to authenticate with incorrect password");
        }

//        System.out.println("Entering method: " + ctx.getMethod().getName());
//        System.out.println(request.getHeader('Aut'));
        //or logger.info statement
        return ctx.proceed();
    }
}
