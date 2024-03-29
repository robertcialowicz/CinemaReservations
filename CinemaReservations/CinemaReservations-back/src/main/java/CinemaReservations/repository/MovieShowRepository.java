package CinemaReservations.repository;

import CinemaReservations.model.Film;
import CinemaReservations.model.MovieShow;
import CinemaReservations.model.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class MovieShowRepository {

    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

    public MovieShowRepository() {
    }

    public MovieShow movieShow(Long id){
        return em.find(MovieShow.class, id);
    }

    @Transactional(REQUIRED)
    public MovieShow create(MovieShow movieShow){
        em.persist(movieShow);
        em.getReference(Film.class, movieShow.getFilm()).addToMovieshowsList(movieShow.getId());
        return movieShow;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
        MovieShow m = em.find(MovieShow.class, id);
        Film f = em.find(Film.class, m.getFilm());

        //remove all child reservations
        for (Long reservation : m.getReservations()){
            em.remove(em.find(Reservation.class,reservation));
        }

        //TODO update entry in parent Film entity
        f.getMovieShowsAsObjects().remove(m);

        em.remove(m);
    }

    public List<MovieShow> findAll(){
        TypedQuery<MovieShow> query = em.createQuery("SELECT b FROM MovieShow b ORDER BY b.id",MovieShow.class);
        return query.getResultList();
    }

    public Long countAll(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM MovieShow b", Long.class);
        return query.getSingleResult();
    }

    public MovieShow find(Long id){
        return em.find(MovieShow.class, id);
    }

}
