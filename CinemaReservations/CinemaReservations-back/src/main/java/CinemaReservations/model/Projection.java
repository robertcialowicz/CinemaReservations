package CinemaReservations.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Projection {
    @Id
    @GeneratedValue
    private Long id;

    private Film film;

    private MovieTiming timing;

    private TheatreHall hall;

    //TODO konstruktor porjkcji nie może tworzyć nowych klas, tylko szukać już stworzonych obiektów
    public Projection(Film film, String day, String time, Long rows, Long columns) {
        this.film = film;
        this.timing = new MovieTiming(day,time);
        this.hall = new TheatreHall(rows,columns);

    }


}
