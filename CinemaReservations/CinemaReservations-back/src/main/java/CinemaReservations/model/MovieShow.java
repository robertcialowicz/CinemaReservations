package CinemaReservations.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MovieShow {
    @Id
    @GeneratedValue
    private Long id;

    private Film filmId;

    private MovieTime movieTime;

    private TheatreHall theatreHall;


    public MovieShow(Long filmId, String date, String time, Long theatreHallId){
        this.movieTime = new MovieTime(date, time);

    }

    //TODO konstruktor porjkcji nie może tworzyć nowych klas, tylko szukać już stworzonych obiektów



}
