package CinemaReservations.model;

import CinemaReservations.utils.MovieTime;
import com.fasterxml.jackson.annotation.*;

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

    @Column(name = "SEATS_RESERVATION_STATUS", length = 1000)
    private String seatsReservationStatus;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILM_ID")
    private Film film;

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
                ", seatsReservationStatus='" + seatsReservationStatus + '\'' +
                ", film=" + film.getId() +
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

    public String getSeatsReservationStatus() {
        return seatsReservationStatus;
    }

    public void setSeatsReservationStatus(String seatsReservationStatus) {
        this.seatsReservationStatus = seatsReservationStatus;
    }

    public Long getFilm() { return film.getId(); }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Long> getReservations() {
        List<Long> Ids = new ArrayList<Long>();
        for(Reservation reservation : reservations){
            Ids.add(reservation.getId());
        }
        return Ids;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
