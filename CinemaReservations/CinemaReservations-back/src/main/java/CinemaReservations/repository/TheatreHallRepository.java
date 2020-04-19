package CinemaReservations.repository;

import CinemaReservations.model.TheatreHall;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class TheatreHallRepository {

    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

    //TODO Default constructor to be created

    public TheatreHall find(Long id){
        return em.find(TheatreHall.class, id);
    }

    @Transactional(REQUIRED)
    public TheatreHall create(TheatreHall theatreHall){
        em.persist(theatreHall);
        return theatreHall;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
        em.remove(em.getReference(TheatreHall.class, id));
    }

    public List<TheatreHall> findAll(){
        TypedQuery<TheatreHall> query = em.createQuery("SELECT b FROM TheatreHall b ORDER BY b.id",TheatreHall.class);
        return query.getResultList();
    }

    public Long countAll(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM TheatreHall b", Long.class);
        return query.getSingleResult();
    }

}
