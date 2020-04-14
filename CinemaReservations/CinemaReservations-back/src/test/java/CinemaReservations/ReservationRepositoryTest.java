package CinemaReservations;

import CinemaReservations.repository.ReservationRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ReservationRepositoryTest {

    private static final String APPLICATION_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<application xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application_6.xsd\" version=\"6\">"            + "<display-name>org.acme.project</display-name>"
            // the WAR must be added to the application.xml !
            + "<module><web><web-uri>test.war</web-uri><context-root>/test</context-root></web></module>"
            + "</application>";

    @Test
    public void create() {
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ReservationRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }


}
