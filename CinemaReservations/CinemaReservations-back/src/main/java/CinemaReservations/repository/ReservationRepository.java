package CinemaReservations.repository;

import CinemaReservations.model.Reservation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class ReservationRepository {

    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

    //TODO Default constructor to be created

    public Reservation find(Long id){
        return em.find(Reservation.class, id);
    }

    @Transactional(REQUIRED)
    public Reservation create(Reservation reservation){
        em.persist(reservation);
        return reservation;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
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
