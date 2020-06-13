package CinemaReservations.repository;

import CinemaReservations.model.Film;
import CinemaReservations.model.MovieShow;
import CinemaReservations.model.Reservation;

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
        TypedQuery<MovieShow> query = em.createQuery("SELECT FROM MovieShow WHERE film = " + id , MovieShow.class);
        for(MovieShow movieShow : query.getResultList()){
            em.remove(em.getReference(MovieShow.class, movieShow.getId()));
        }
        em.remove(em.getReference(Film.class, id));
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
