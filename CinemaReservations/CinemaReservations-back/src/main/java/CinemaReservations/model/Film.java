package CinemaReservations.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity(name = "Film")
public class Film {

    @Id
    @GeneratedValue
    @Column(name = "FILM_ID")
    private Long id;

    @Column(name = "TITLE", length = 1000)
    private String title;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "TYPEOFFILM", length = 1000)
    private String TYPEOFFILM;

    @Column(name = "DIRECTOR", length = 1000)
    private String director;

    @Column(name = "SCENARIO", length = 1000)
    private String scenario;

    @Column(name = "RELEASE_DATE", length = 1000)
    private String releaseDate;

    @Column(name = "CASTS", length = 1000)
    private String casts;

    @Column(name = "AGE_LIMIT", length = 1000)
    private Long ageLimit;

    @OneToMany(mappedBy = "film")
    private List<MovieShow> movieShows = new ArrayList<>();

    public Film(){
    }

    public Film(String title, String description){
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + TYPEOFFILM + '\'' +
                ", director='" + director + '\'' +
                ", scenario='" + scenario + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", cast='" + casts + '\'' +
                ", ageLimit=" + ageLimit +
                ", movieShows=" + movieShows +
                '}';
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getType() { return TYPEOFFILM; }

    public void setType(String type) { this.TYPEOFFILM = type; }

    public String getDirector() { return director; }

    public void setDirector(String director) { this.director = director; }

    public String getScenario() { return scenario; }

    public void setScenario(String scenario) { this.scenario = scenario; }

    public String getReleaseDate() { return releaseDate; }

    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getCast() { return casts; }

    public void setCast(String cast) { this.casts = cast; }

    public Long getAgeLimit() { return ageLimit; }

    public void setAgeLimit(Long ageLimit) { this.ageLimit = ageLimit; }

    public List<MovieShow> getMovieShows() { return movieShows; }

    public void setMovieShows(List<MovieShow> movieShows) { this.movieShows = movieShows; }
}
