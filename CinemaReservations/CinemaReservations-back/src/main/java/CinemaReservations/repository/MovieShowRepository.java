package CinemaReservations.repository;

import CinemaReservations.model.MovieShow;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class MovieShowRepository {

    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

    public MovieShow movieShow(Long id){
        return em.find(MovieShow.class, id);
    }

    @Transactional(REQUIRED)
    public MovieShow create(MovieShow movieShow){
        em.persist(movieShow);
        return movieShow;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
        em.remove(em.getReference(MovieShow.class, id));
    }

    public List<MovieShow> findAll(){
        TypedQuery<MovieShow> query = em.createQuery("SELECT b FROM MovieShow b ORDER BY b.id",MovieShow.class);
        return query.getResultList();
    }

    public Long countAll(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM MovieShow b", Long.class);
        return query.getSingleResult();
    }

}
