package CinemaReservations.repository;

import CinemaReservations.model.MovieShow;
import CinemaReservations.model.Reservation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
        //update film entry with new movieshows list
        List<MovieShow> newMovieShowList = new ArrayList<MovieShow>();
        newMovieShowList = movieShow.getFilm().getMovieShows();
        newMovieShowList.add(movieShow);
        movieShow.getFilm().setMovieShows(newMovieShowList);

        em.persist(movieShow.getFilm());
        em.persist(movieShow);
        return movieShow;
    }

    @Transactional(REQUIRED)
    public void delete(Long id){
        //delete all reservations referenced to this movieshow
        TypedQuery<Reservation> query = em.createQuery("SELECT FROM Reservation WHERE movieshow = " + id , Reservation.class);
        for(Reservation reservation : query.getResultList()){
            em.remove(em.getReference(Reservation.class, reservation.getId()));
        }

        //update film entry with new movieshows list
        List<MovieShow> newMovieShowList = new ArrayList<MovieShow>();
        newMovieShowList = em.getReference(MovieShow.class, id).getFilm().getMovieShows();
        newMovieShowList.remove(em.getReference(MovieShow.class, id));
        em.getReference(MovieShow.class, id).getFilm().setMovieShows(newMovieShowList);

        em.persist(em.getReference(MovieShow.class, id).getFilm());
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

    public MovieShow find(Long id){
        return em.find(MovieShow.class, id);
    }

}
