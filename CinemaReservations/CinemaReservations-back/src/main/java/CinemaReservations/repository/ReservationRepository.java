package CinemaReservations.repository;

import CinemaReservations.model.MovieShow;
import CinemaReservations.model.Reservation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
        //TODO rework with new utility class
        //get single seats as a list from whole String e.g. "A1,A2,A3" -> ["A1","A2","A3"]
        List<String> allReservedSeats = new ArrayList<String>();
        Pattern pattern = Pattern.compile("([A-J][0-9]{1,2})");
        Matcher matcher = pattern.matcher(reservation.getBookedSeats());
        while(matcher.find()) {
            allReservedSeats.add(matcher.group());
        }
        //TODO rework with new utility class
        //foreach reserved seat check if it is not already reserved } if not expand list of booked seats in given movieshow
        for(String toBeChecked : allReservedSeats){
            if(!reservation.getMovieShow().getSeatsReservationStatus().toLowerCase().contains(toBeChecked.toLowerCase())){
                throw new IllegalArgumentException("Choosen seats are already reserved!");
            }
            else reservation.getMovieShow().setSeatsReservationStatus((new StringBuilder())
                    .append(reservation.getMovieShow().getSeatsReservationStatus())
                    .append(",")
                    .append(reservation.getBookedSeats())
                    .toString());
        }

        em.persist(reservation);
        return reservation;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
        String newReservedSeatsString;
        newReservedSeatsString = em.getReference(Reservation.class, id).getMovieShow().getSeatsReservationStatus();
        //newReservedSeatsString.remove(em.getReference(Reservation.class, id).getBookedSeats());
        //em.getReference(Reservation.class, id).getMovieShow().setSeatsReservationStatus(newReservedSeatsString);
        //TODO rework with new utility class
        em.persist(em.getReference(Reservation.class, id).getMovieShow());
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
