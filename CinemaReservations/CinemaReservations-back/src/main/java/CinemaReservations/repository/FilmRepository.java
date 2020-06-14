package CinemaReservations.repository;

import CinemaReservations.model.Film;
import CinemaReservations.model.MovieShow;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class FilmRepository {

    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

    public FilmRepository() {
    }

    public Film find(Long id){
        return em.find(Film.class, id);
    }

    @Transactional(REQUIRED)
    public Film create(Film film){
        em.persist(film);
        return film;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
        //remove all child reservations
        Film a = em.find(Film.class, id);
        for (Long movieShow : a.getMovieShows()){
            em.remove(em.find(MovieShow.class,movieShow));
        }

        em.remove(a);
    }

    public List<Film> findAll(){
        TypedQuery<Film> query = em.createQuery("SELECT b FROM Film b ORDER BY b.id",Film.class);
        return query.getResultList();
    }

    public Long countAll(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Film b", Long.class);
        return query.getSingleResult();
    }

}
