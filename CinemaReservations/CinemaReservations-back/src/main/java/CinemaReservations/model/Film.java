package CinemaReservations.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity(name = "Film")
public class Film {

    //TODO Validation of data provided in constructors

    @Id
    @GeneratedValue
    @Column(name = "FILM_ID")
    private Long id;

    @Column(name = "TITLE", length = 1000)
    private String title;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @OneToMany(mappedBy = "film")
    private List<MovieShow> movieShows = new ArrayList<>();

    public Film(){
    }

    public Film(String title, String description){
        //this.id = new Random(1234).nextLong();
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieShow> getMovieShows() {
        return movieShows;
    }

    public void setMovieShows(List<MovieShow> movieShows) {
        this.movieShows = movieShows;
    }
}
