package CinemaReservations.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class MovieTime {

    //TODO this bean to be used when dealing with MovieShow.MovieTime attribute

    private LocalTime time;
    private LocalDate date;
    private DayOfWeek dayOfWeek;

    MovieTime(String date, String time){
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public String getTime() {
        return time.toString();
    }

    public String getDate() {
        return date.toString() + date.getDayOfWeek().toString();
    }

}
