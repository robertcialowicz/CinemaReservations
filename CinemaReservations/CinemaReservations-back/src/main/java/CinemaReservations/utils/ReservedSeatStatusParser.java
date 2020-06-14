package CinemaReservations.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservedSeatStatusParser {

    public boolean areSeatsFree(String input, String toCheck){
        List<String> toCheckAsList = Arrays.asList(toCheck.split(","));
        List<String> inputAsList = Arrays.asList(input.split(","));

        for(String inputSingle : inputAsList) {
            for(String toCheckSingle : toCheckAsList) {
                if (inputSingle.equals(toCheckSingle))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public String addToReserved(String input, String toAdd){
        return (new StringBuilder()).append(input).append(",").append(toAdd).toString();
    }

    public String removeFromReserved(String input, String toRemove){
        List<String> inputAsList = Arrays.asList(input.split(","));
        List<String> toRemoveAsList = Arrays.asList(toRemove.split(","));

        for (String toRemoveSingle : toRemoveAsList){
            Integer temp = inputAsList.indexOf(toRemoveSingle);
            if (temp > -1) inputAsList.remove(temp);
        }

        return String.join(",",inputAsList);
    }

}
