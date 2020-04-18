package CinemaReservations.model;

import javax.persistence.*;
import javax.swing.text.html.CSS;
import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.groups.ConvertGroup;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity(name = "TheatreHall")
public class TheatreHall {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "SEATS_RESERVATION_STATUS", length = 1000)
    private String seatsReservationMatrixAsString;

    @Column(name = "WHICH_HALL")
    private Long whichHall;

    @OneToOne
    private MovieShow movieShow;

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

        SeatStatus[][] seatsReservationMatrix = new SeatStatus[columns][rows];

        //TODO write function freeAllSeats operating on String instead of 2D array
        for (int i = 0 ; i < rows - 1; i++){
            for (int j = 0 ; j < columns - 1; j++){
                seatsReservationMatrix[i][j] = SeatStatus.FREE;
            }
        }

        this.seatsReservationMatrixAsString = Arrays.stream(seatsReservationMatrix)
                .map(Arrays::toString)
                .collect(Collectors.joining(","));
    }

    //TODO function to parse String to SeatStatus[][]
    //TODO Write getters and setters which are operating on String
    //TODO i.e. reserveSeat(2,1) has to change values in String from [FREE,FREE,FREE],[FREE,FREE,FREE],[FREE,FREE,FREE] to [FREE,FREE,FREE],[FREE,FREE,FREE],[FREE,OCCUPIED,FREE]


    @Override
    public String toString() {
        return "TheatreHall{" +
                "id=" + id +
                ", SeatsReservationStatus='" + seatsReservationMatrixAsString + '\'' +
                '}';
    }



}
