package CinemaReservations.model;

import CinemaReservations.utils.MovieTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MovieShow")
public class MovieShow {

    @Id
    @GeneratedValue
    @Column(name = "MOVIESHOW_ID")
    private Long id;

    @Column(name = "DATE", length = 1000)
    private String date;

    @Column(name = "TIME", length = 1000)
    private String time;

    @ManyToOne
    @JoinColumn(name = "FILM_ID")
    private Film film;

    @OneToOne(mappedBy = "movieShow")
    private TheatreHall theatreHall;

    @OneToMany(mappedBy = "movieShow")
    private List<Reservation> reservations = new ArrayList<>();

    public MovieShow(){
    }

    @Override
    public String toString() {
        return "MovieShow{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", film=" + film +
                ", theatreHall=" + theatreHall +
                ", reservations=" + reservations +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public TheatreHall getTheatreHall() {
        return theatreHall;
    }

    public void setTheatreHall(TheatreHall theatreHall) {
        this.theatreHall = theatreHall;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
