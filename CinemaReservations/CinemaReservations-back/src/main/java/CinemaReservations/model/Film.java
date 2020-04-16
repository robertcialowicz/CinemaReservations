package CinemaReservations.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Film {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    Film(Long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

}
