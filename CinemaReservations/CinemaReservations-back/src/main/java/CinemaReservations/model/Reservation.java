package CinemaReservations.model;
import javax.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BOOKEDSEATS")
    private String bookedSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIESHOW_ID")
    private MovieShow movieShow;

    public Reservation() {
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", bookedSeats='" + bookedSeats + '\'' +
                ", movieShow=" + movieShow.getId() +
                '}';
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getBookedSeats() { return bookedSeats; }

    public void setBookedSeats(String bookedSeats) { this.bookedSeats = bookedSeats; }

    public Long getMovieShow() { return movieShow.getId(); }

    public void setMovieShow(Long movieShow) {
        if(movieShow > 0){
            EntityManager em = Persistence.createEntityManagerFactory("CinemaReservationsPU").createEntityManager();
            this.movieShow = em.getReference(MovieShow.class, movieShow);
        } else {
            this.movieShow = null;
        }
    }

    public void setMovieShowAsObject(MovieShow movieShow) {
        this.movieShow = movieShow;
    }
}
