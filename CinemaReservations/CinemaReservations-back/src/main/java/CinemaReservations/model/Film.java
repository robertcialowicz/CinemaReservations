package CinemaReservations.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Film {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    public Film(){
    }

    Film(String title, String description){
        this.title = title;
        this.description = description;
    }

}
