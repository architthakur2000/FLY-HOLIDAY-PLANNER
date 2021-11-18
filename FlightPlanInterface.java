import java.util.ArrayList;
import java.util.List;

/**
 * Interface that keeps track of the airports, cost, and length of a certain
 * path
 */
public class FlightPlanInterface {
    List<AirportInterface> flightPlan = new ArrayList<>();
    int cost;
    int duration;

    /**
     * Constructor that turns the list of edges into data that can be accessed
     * @param airportPath 
     */
    public FlightPlanInterface(List<AirportInterface> airportPath, int totalDuration, int totalCost){
        // Transfers over the airportPath list into the flightPlan list
        for(int i = 0; i < airportPath.size(); i++){
            flightPlan.add(airportPath.get(i));
        }
        this.duration = totalDuration;
        this.cost = totalCost;
    }

    /**
     * returns a list of strings that is the flight path
     */
    public List<AirportInterface> getPath(){
        return flightPlan;
    }

    /**
     * 
     * @return the cost of all of the flights added together
     */
    public int getCost() {
        return cost;
    }

    /**
     * 
     * @return the length of all of the flights added together
     */
    public int getTime() {
        return duration;
    }
}
//AUTHOR: ARCHIT THAKUR
