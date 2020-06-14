package CinemaReservations.repository;

import CinemaReservations.model.Film;
import CinemaReservations.model.MovieShow;
import CinemaReservations.model.Reservation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import CinemaReservations.utils.ReservedSeatStatusParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class ReservationRepository {

    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

    public ReservationRepository() {
    }

    public Reservation find(Long id){
        return em.find(Reservation.class, id);
    }

    @Transactional(REQUIRED)
    public Reservation create(Reservation reservation) throws IllegalArgumentException {
        ReservedSeatStatusParser helper = new ReservedSeatStatusParser();
        //TODO check if free seats are for sure available
        if(helper.areSeatsFree(em.getReference(MovieShow.class, reservation.getMovieShow()).getSeatsReservationStatus(),reservation.getBookedSeats()))
            throw new IllegalArgumentException("Chosen seats are already occupied!");

        //TODO update reserved seats in parent movieshow
        em.getReference(MovieShow.class,reservation.getMovieShow()).setSeatsReservationStatus(
                helper.addToReserved(em.getReference(MovieShow.class, reservation.getMovieShow()).getSeatsReservationStatus(),reservation.getBookedSeats()));

        //update field in DB and parent movieshow entity
        em.persist(reservation);
        em.getReference(MovieShow.class, reservation.getMovieShow()).addToReservationList(reservation.getId());
        return reservation;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
        //TODO update reserved seats in parent movieshow
        ReservedSeatStatusParser helper = new ReservedSeatStatusParser();
        em.getReference(MovieShow.class,em.getReference(Reservation.class, id).getMovieShow()).setSeatsReservationStatus(
                helper.removeFromReserved(em.getReference(MovieShow.class, em.getReference(Reservation.class, id).getMovieShow()).getSeatsReservationStatus(),em.getReference(Reservation.class, id).getBookedSeats()));

        //update field in DB and parent movieshow entity
        em.getReference(MovieShow.class, em.getReference(Reservation.class, id).getMovieShow()).removeFromReservationList(id);
        em.remove(em.getReference(Reservation.class, id));
    }

    public List<Reservation> findAll(){
        TypedQuery<Reservation> query = em.createQuery("SELECT b FROM Reservation b ORDER BY b.id",Reservation.class);
        return query.getResultList();
    }

    public Long countAll(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Reservation b", Long.class);
        return query.getSingleResult();
    }


}
