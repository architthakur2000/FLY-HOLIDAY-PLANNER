
// Name: Jackson Wolf
// Email: jpwolf@wisc.edu
// Team: Red
// Group: CA
// TA: Xi Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class BackendTest {
    /**
     * Test of the flight plan interface and it's methods
     */
    @Test
    public void FlightPlanInterfaceTest(){
        List<AirportInterface> airportPath = new ArrayList<>();
        //Create objects for LAX, MKE, ATL, and FRA
        AirportInterface lax = new Airport("Los Angeles International Airport", "LAX", "Los Angeles");
        AirportInterface mke = new Airport("Milwaukee International Airport", "MKE", "Milwaukee");
        AirportInterface atl = new Airport("William T. Sherman International Airport", "ATL", "Atlanta");
        AirportInterface fra = new Airport("Die Aeroportenfrankfurten", "FRA", "Frankfurt");

        airportPath.add(lax);
        airportPath.add(mke);
        airportPath.add(atl);
        airportPath.add(fra);
        FlightPlanInterface testFlightPlan = new FlightPlanInterface(airportPath, 59, 73);

        assertEquals(testFlightPlan.getTime(), 59);
        assertTrue(testFlightPlan.getPath().get(2).getCode().equals("ATL"));
    }

    /**
     * test of the cheapestFlightPath method in the BackendInterface
     */
    @Test
    public void testCheapestFlight(){
        //Airport List
        List<AirportInterface> airports = new ArrayList<>();
        AirportInterface MKE = new Airport("Milwuakee International Airport","MKE","Milwaukee,WI, USA");
        AirportInterface DEN = new Airport("Denver International Airport","DEN","Denver");
        AirportInterface MSN = new Airport("Dane County Regional Airport","MSN","Madison");
        AirportInterface LMO = new Airport("Vance Brand Airport","LMO","Longmont");
        AirportInterface SFO = new Airport("San Francisco International Airport","SFO","San Francisco");
        airports.add(MKE);
        airports.add(DEN);
        airports.add(MSN);
        airports.add(LMO);
        airports.add(SFO);
        
        // Airport flightData
        List<String[]> flightData = new ArrayList<>();
        flightData.add(new String[]{"SFO","MKE","11","1"});
        flightData.add(new String[]{"DEN","SFO","10","5"});
        flightData.add(new String[]{"DEN","MKE","1","8"});
        flightData.add(new String[]{"MKE","MSN","20","6"});
        flightData.add(new String[]{"MSN","DEN","4","7"});
        flightData.add(new String[]{"MSN","LMO","6","2"});
        flightData.add(new String[]{"LMO","MKE","16","4"});

        BackendInterface testBackend = new BackendInterface(airports, flightData);
        FlightPlanInterface pathToFind = testBackend.cheapestFlightPath(new String[]{"DEN","MKE"});
        assertEquals(pathToFind.getCost(), 6);
    }

    /**
     * test of the shortestFlightPath method in the BackendInterface
     */
    @Test
    public void testShortestFlight(){
        //Airport List
        List<AirportInterface> airports = new ArrayList<>();
        AirportInterface MKE = new Airport("Milwuakee","MKE","Milwaukee,WI, USA");
        AirportInterface DEN = new Airport("Denver International Airport","DEN","Denver");
        AirportInterface MSN = new Airport("Dane County Regional Airport","MSN","Madison");
        AirportInterface LMO = new Airport("Vance Brand Airport","LMO","Longmont");
        AirportInterface SFO = new Airport("San Francisco International Airport","SFO","San Francisco");
        airports.add(MKE);
        airports.add(DEN);
        airports.add(MSN);
        airports.add(LMO);
        airports.add(SFO);
        
        // Airport flightData
        List<String[]> flightData = new ArrayList<>();
        flightData.add(new String[]{"SFO","MKE","11","1"});
        flightData.add(new String[]{"DEN","SFO","10","5"});
        flightData.add(new String[]{"DEN","MKE","1","8"});
        flightData.add(new String[]{"MKE","MSN","20","6"});
        flightData.add(new String[]{"MSN","DEN","4","7"});
        flightData.add(new String[]{"MSN","LMO","6","2"});
        flightData.add(new String[]{"LMO","MKE","16","4"});

        BackendInterface testBackend = new BackendInterface(airports, flightData);
        String[] flights = {"MSN", "MKE"};
        FlightPlanInterface pathToFind = testBackend.shortestFlightPath(flights);
        assertEquals(pathToFind.getTime(), 5);
    }
}

