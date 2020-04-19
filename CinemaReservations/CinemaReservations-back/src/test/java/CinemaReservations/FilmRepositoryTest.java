package CinemaReservations;

import CinemaReservations.model.Film;
import CinemaReservations.model.MovieShow;
import CinemaReservations.model.Reservation;
import CinemaReservations.model.TheatreHall;
import CinemaReservations.repository.FilmRepository;
import CinemaReservations.repository.ReservationRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class FilmRepositoryTest {

    @Inject
    private FilmRepository filmRepository;


    @Test
    public void create() throws Exception {
        //Tests counting reservations
        Assert.assertEquals(Long.valueOf(0), filmRepository.countAll());
        Assert.assertEquals(Long.valueOf(0), Long.valueOf(filmRepository.findAll().size()));

        //Create a film
        Film film = new Film("Avatar","long");
        film = filmRepository.create(film);
        Long filmId = film.getId();
        assertNotNull(filmId);

        Film filmFound = filmRepository.find(filmId);
        assertEquals("Avatar",filmFound.getTitle());

        Assert.assertEquals(Long.valueOf(1), filmRepository.countAll());

        //TODO add some more tests to be sure every element in DB is working at its full path -> create -> find -> edit -> delete
        //TODO same test for another object i.e. Reservation, MovieShow, TheatreHall
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(FilmRepository.class)
                .addClass(Film.class)
                .addClass(MovieShow.class)
                .addClass(TheatreHall.class)
                .addClass(Reservation.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

}
