// This is the BackendInterface
import java.util.List;
import java.io.Reader;
import java.util.ArrayList;

public class BackendInterface {
    List<AirportInterface> airports = new ArrayList<AirportInterface>();
    Graph<AirportInterface> timeGraph = new Graph<>();
    Graph<AirportInterface> costGraph = new Graph<>();

    /**
     * Constructor that initializes the graphs by passing a reader to the data
     * wrangelr code
     */
    public BackendInterface(Reader airportReader, Reader flightReader) {
        List<String[]> flightData = new ArrayList<>();
        AirportDataReader reader = new AirportDataReader();
        try {
            // initialize the methods that gather the data and give fields their values
            airports = reader.readAirportData(airportReader);
            flightData = reader.readFlightData(flightReader);
        } catch (Exception e) {
            System.out.println("airportReader could not read data");
        }

        // insert all of the airports as vertices
        for (int i = 0; i < airports.size(); i++) {
            // String array of code , name , and location
            timeGraph.insertVertex(airports.get(i));
            costGraph.insertVertex(airports.get(i));
        }

        // insert all of the flight data as edges
        for (int i = 0; i < flightData.size(); i++) {
            // turning the airport names into string arrays with the data inside
            AirportInterface one = getAirport(flightData.get(i)[0]);
            AirportInterface two = getAirport(flightData.get(i)[1]);
           

            // putting string arrays into graphs as edges
            timeGraph.insertEdge(one, two, Integer.parseInt(flightData.get(i)[2]));
            costGraph.insertEdge(one, two, Integer.parseInt(flightData.get(i)[3]));
        }
    }

    /**
     * Constructor for testing purposes
     */
    public BackendInterface(List<AirportInterface> airports, List<String[]> flightData) {

        for (int i = 0; i < airports.size();i++) {
          this.airports.add(airports.get(i));
        }
        
        // insert all of the airports as vertices
        for (int i = 0; i < airports.size(); i++) {
            // String array of code , name , and location
          timeGraph.insertVertex(airports.get(i));
          costGraph.insertVertex(airports.get(i));
        }

        // insert all of the flight data as edges
        for (int i = 0; i < flightData.size(); i++) {
            // turning the airport names into string arrays with the data inside
          
          AirportInterface one = getAirport(flightData.get(i)[0]);
          AirportInterface two = getAirport(flightData.get(i)[1]);

            // putting string arrays into graphs as edges
          timeGraph.insertEdge(one, two, Integer.parseInt(flightData.get(i)[2]));
          costGraph.insertEdge(one, two, Integer.parseInt(flightData.get(i)[3]));
            
          
        }
    }

    /**
     * 
     * @param destinations the string of all the desired destinations
     * @return the FlightPlanInterface with all of the flight data
     * @throws IllegalAccessException if less than 2 destinations are passed into
     *                                the method
     */
    public FlightPlanInterface shortestFlightPath(String[] destinations) throws IllegalArgumentException {
        int numDestinations = destinations.length;
        // if an illegal argument
        if (numDestinations < 2) {
            throw new IllegalArgumentException("Destination list is shorter than 2 airports");
        }
        // puts all of the shortest paths between the multiple destinations into one
        // array of Paths
        ArrayList<Graph<AirportInterface>.Path> shortestPaths = new ArrayList<>();
        List<AirportInterface> totalPath = new ArrayList<>();
        //Variables to store total cost and time
        int totTime = 0;
        int totCost = 0;
        for (int i = 0; i < numDestinations - 1; i++) {
            AirportInterface one = getAirport(destinations[i]);
            AirportInterface two = getAirport(destinations[i + 1]);
            shortestPaths.add(timeGraph.dijkstrasShortestPath(one, two));
            totTime += timeGraph.getPathCost(one, two);
            totCost = costGraph.getPathCost(one, two);
            // adds the edges of the most recent path into the total shortest path
            for (int j = 0; j < shortestPaths.get(i).dataSequence.size(); j++) {
                totalPath.add(shortestPaths.get(i).dataSequence.get(j));
            }
        }

        return new FlightPlanInterface(totalPath, totTime, totCost);
    }

    /**
     * 
     * @param destinations the string of all the return the FlightPlanInterface with
     *                     all of the flight data
     * 
     * @throws IllegalAccessException if less tha n 2 destinations are passed into
     *                                the method
     */
    public FlightPlanInterface cheapestFlightPath(String[] destinations) throws IllegalArgumentException {
        int numDestinations = destinations.length;
        // if an illegal argument
        if (numDestinations < 2) {
            throw new IllegalArgumentException("Destination list is shorter than 2 airports");
        }
        //Variables to store total cost and time
        int totTime = 0;
        int totCost = 0;
        // puts all of the shortest paths between the multiple destinations into one
        // array of Paths
        ArrayList<Graph<AirportInterface>.Path> shortestPaths = new ArrayList<>();
        List<AirportInterface> totalPath = new ArrayList<>();
        for (int i = 0; i < numDestinations - 1; i++) {
            AirportInterface one = getAirport(destinations[i]);
            AirportInterface two = getAirport(destinations[i + 1]);
            shortestPaths.add(costGraph.dijkstrasShortestPath(one, two));
            totTime += timeGraph.getPathCost(one, two);
            totCost = costGraph.getPathCost(one, two);
            // adds the edges of the most recent path into the total shortest path
            for (int j = 0; j < shortestPaths.get(i).dataSequence.size(); j++) {
                totalPath.add(shortestPaths.get(i).dataSequence.get(j));
            }
        }

        return new FlightPlanInterface(totalPath, totTime, totCost);
    }

    /**
     * Returns a list of all the airport interfaces
     * 
     * @return the list of Airports
     */
    public List<AirportInterface> airportNameList() {
        return airports;
    }

    /**
     * The the Airport object of the airport corresponding to the name given to this
     * method
     * 
     */
    public AirportInterface getAirport(String code) {
      
        for (int i = 0; i < this.airports.size(); i++) {
            if (this.airports.get(i).getCode().equals(code)) {
                return airports.get(i);
            }
        }
        return null;
    }

}
// Author: ARCHIT THAKUR