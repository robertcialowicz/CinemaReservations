package CinemaReservations.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TheatreHall {
    @Id
    private Long id;

    private Boolean[][] seatsReservationMatrix;

    TheatreHall(Long rows, Long columns){
        seatsReservationMatrix = new Boolean[Math.toIntExact(rows)][Math.toIntExact(columns)];
        for(Boolean seats[] : seatsReservationMatrix){
            for(Boolean seat : seats){
                seat = Boolean.FALSE;
            }
        }
    }

}
