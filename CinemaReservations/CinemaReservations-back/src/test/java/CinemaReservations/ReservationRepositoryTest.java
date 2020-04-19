package CinemaReservations;

import CinemaReservations.model.Reservation;
import CinemaReservations.repository.ReservationRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class ReservationRepositoryTest {

    @Inject
    private ReservationRepository reservationRepository;

    @Test
    public void create() throws Exception {
        //Tests counting reservations
        Assert.assertEquals(Long.valueOf(0), reservationRepository.countAll());
        Assert.assertEquals(Long.valueOf(0), Long.valueOf(reservationRepository.findAll().size()));
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ReservationRepository.class)
                .addClass(Reservation.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }


}
