// This Class consists the Data Wrangling Tests for the project. 

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DataWranglerTests {
    /**
     * Tests the basic functionality of the Airport class, which extends the AirportInterface
     * Creates a couple airport objects
     * Tests functionality of getName(), getCode(), and getLocation()
     */
    @Test
    public void testAirportClass() {
        //Vance Brand Airport is an airport in Longmont, Colorado
        AirportInterface testAirport = new Airport("Vance Brand Airport", "LMO", "Longmont");
        assertEquals(testAirport.getName(), "Vance Brand Airport");
        assertEquals(testAirport.getCode(), "LMO");
        assertEquals(testAirport.getLocation(), "Longmont");
    }

    /**
     * Tests the following:
     * 1. All Airports defined in AirportData.csv have 3-letter codes associated with them
     * 2. All the three-letter Airport codes referenced in FlightData.csv are valid codes defined in AirportData
     */
    @Test
    public void testFlightValidity() {
        try {
            //First, get all valid airport codes by iterating through AirportData.csv
            File airportData = new File("AirportData.csv");
            ArrayList<String> airportCodes = new ArrayList<>();
            FileReader reader = new FileReader(airportData);
            BufferedReader airportReader = new BufferedReader(reader);
            String cur;
            //Iterate through AirportData.csv and add all airport codes to an ArrayList
            while ((cur = airportReader.readLine()) != null) {
                //Split the current line by commas
                String[] splitLine = cur.split(",");
                //Get the airport code, which should the 2nd value on each line
                String code = splitLine[1];
                //Check that every code is three letters long
                assertEquals(code.length(), 3);
                //Add each code to the code list
                airportCodes.add(code);
            }
            //Now, iterate through FlightData and check that all codes there are in the list of codes from AirportData
            File flightData = new File("FlightData.csv");
            reader = new FileReader(flightData);
            BufferedReader flightReader = new BufferedReader(reader);
            while((cur = flightReader.readLine()) != null) {
                //Split the current line by commas
                String[] splitLine = cur.split(",");
                //Each line should have two airport codes, respectively the first and second values on the line
                String firstCode = splitLine[0];
                String secondCode = splitLine[1];
                //Assert that both codes are valid and were defined in AirportData
                assertTrue(airportCodes.contains(firstCode));
                assertTrue(airportCodes.contains(secondCode));
            }
        }
        //If there are any unexpected exceptions, fail the test
        catch (FileNotFoundException f) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
    }

    /**
     * Tests that AirportDataReader's ReadAirportData works as intended
     * When passed AirportData.csv as input, method should return a list of 31 AirportInterface objects
     * This method tests the following:
     * 1. readAirportData() runs without errors
     * 2. readAirportData() returns a list of 31 airport interfaces, representing all the airports described in AirportData
     * 3. None of the airports have any null characteristics
     */
    @Test
    public void testReadAirportData(){
        try{
            //Create a AirportDataReader object
            AirportDataReader reader = new AirportDataReader();
            //Create a reader to read the file
            FileReader fileReader = new FileReader("AirportData.csv");
            //Create a List of AirportInterfaces to store all the interfaces
            List<AirportInterface> allAirports = reader.readAirportData(fileReader);
            //Check that the length is 31, as there are 31 airports listed in AirportData.csv
            assertEquals(allAirports.size(), 31);
            //Iterate through the list of AirportInterfaces that were returned by readAirportData()
            for(int i = 0; i < allAirports.size(); i++){
                //Assert that each one has a valid name, code, and location (e.g. not null)
                assertNotEquals(null, allAirports.get(i).getName());
                assertNotEquals(null, allAirports.get(i).getCode());
                assertNotEquals(null, allAirports.get(i).getLocation());
            }
        }
        catch(Exception e){
            //If there are any unexpected exceptions, test should fail
            e.printStackTrace();
            fail();
        }
    }

    /**
    * Tests that AirportDataReader's ReadFlightData works as intended
     * This method tests the following:
     * 1. readFlightData() runs without errors
     * 2. readFlightData() returns a list of 250 four-element string arrays representing flight paths
     * 3. The first two elements of each four-element string array are airport codes
     */
    @Test
    public void testReadFlightData(){
        try{
            //Create a AirportDataReader object
            AirportDataReader reader = new AirportDataReader();
            //Create a reader to read the file
            FileReader fileReader = new FileReader("FlightData.csv");
            //Create a List of String arrays to store all the paths
            List<String[]> allFlights = reader.readFlightData(fileReader);
            //Assert that there are 250 different "flights" in the array
            assertEquals(allFlights.size(), 250);
            //Iterate through the list of String lists that was returned by readFlightData
            for(int i = 0; i < allFlights.size(); i++){
                //Check that each list stored has four elements:
                //1. Source airport code - should be 3-letter string
                //2. Destination airport code - should be a 3-letter string
                //3. Flight time - should be an int in string form
                //4. Flight cost - should be an int in string form
                assertEquals(allFlights.get(i).length, 4);
                //Check that the source airport and destination airport are both 3-character codes
                assertEquals(allFlights.get(i)[0].length(), 3);
                assertEquals(allFlights.get(i)[1].length(), 3);
            }
        }
        catch(Exception e){
            //If there are any unexpected exceptions, test should fail
            fail();
        }
    }
}

//Author: ARCHIT THAKUR