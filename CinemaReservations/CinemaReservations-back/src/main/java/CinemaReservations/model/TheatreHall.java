package CinemaReservations.model;

import javax.persistence.*;
import javax.swing.text.html.CSS;
import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.groups.ConvertGroup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class TheatreHall {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<SeatStatus> ala;

    @Column(name = "seat_grid", columnDefinition = "se[][]")
    private SeatStatus[][] seatsReservationMatrix;

    public TheatreHall(){
    }

    TheatreHall(Long whichHall){

        int columns = 0;
        int rows = 0;

        switch (Math.toIntExact(whichHall)){
            case 1: columns = 10; rows = 10;
                    break;
            case 2: columns = 12; rows = 12;
                    break;
            case 3:
            case 6: columns = 10; rows = 12;
                    break;
            case 4: columns = 12; rows = 10;
                    break;
            case 5: columns = 9; rows = 10;
                    break;
            default:
                    throw new IllegalArgumentException("There is no Hall with given number in this theatre!");
        }

        seatsReservationMatrix = new TheatreSeat[columns][rows];
        freeAllSeats();
    }

    private void freeAllSeats(){
        for(TheatreSeat seats[] : seatsReservationMatrix){
            for(TheatreSeat seat : seats){
                seat = new TheatreSeat(SeatStatus.FREE);
            }
        }
    }
}
