package CinemaReservations.repository;

import CinemaReservations.model.Film;
import CinemaReservations.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class UserRepository {

    @PersistenceContext(unitName = "CinemaReservationsPU")
    private EntityManager em;

    @Transactional
    public void create(User user) {
        em.persist(user);
    }

    public List<User> findAll(){
        TypedQuery<User> query = em.createQuery("SELECT b FROM User b", User.class);

        return query.getResultList();
    }

    public User findByUsername(String username) {
        System.out.println(findAll());
        TypedQuery<User> query  = em.createQuery("SELECT b FROM User b WHERE b.username = :username", User.class)
                .setParameter("username", username);

        return query.getSingleResult();
    }
}
