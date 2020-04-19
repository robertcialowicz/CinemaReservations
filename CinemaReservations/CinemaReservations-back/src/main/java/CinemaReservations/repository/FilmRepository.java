package CinemaReservations.repository;

import CinemaReservations.model.Film;
import CinemaReservations.utils.UtilsJPA;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.*;
import javax.rmi.CORBA.Util;
import javax.transaction.Transactional;
import java.util.List;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class FilmRepository {

    //TODO Injection of Entity manager here doesn't work. First idea is to replace Derby database to mySql
    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

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
