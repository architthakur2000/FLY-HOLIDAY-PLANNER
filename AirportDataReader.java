// The AirportDataReader class implements AirportDataReaderInterface to read the files. 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class AirportDataReader implements AirportDataReaderInterface {
    /**
     * Method to read all the airports specified in the AirportData file and port their data into Airport objects
     * @param inputFileReader is a Reader designed to read in the Airport Data file
     * @return a list of AirportInterfaces, each representing one airport defined in the file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws DataFormatException
     */
    @Override
    public List<AirportInterface> readAirportData(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException {
        //String to temporarily store the next line so it can be checked for non-nullness
        String nextLine;
        //String-array to store the current line as it is parsed
        String[] curLine;
        //The AirportInterface List that will eventually be returned
        List<AirportInterface> retList = new ArrayList<>();
        //The reader, which iterates through the lines of the file and reads them into the list that is returned
        BufferedReader reader = new BufferedReader(inputFileReader);
        //Store the contents of the next line in the curLine string
        //Only continue if it is a) not null and b)not an empty line
        while((nextLine = reader.readLine()) != null && nextLine.length() > 0){
            curLine = nextLine.split(",");
            String airportName = curLine[0];
            String airportCode = curLine[1];
            String airportLoc = curLine[2];
            //Add the new user to the list of Airport Interfaces
            AirportInterface curAirport = new Airport(airportName, airportCode, airportLoc);
            retList.add(curAirport);
        }

        //Return the list of airports
        return retList;
    }

    /**
     * Method to read all the flight paths specified in FlightData.csv and port them into easily-readable four element lists
     * People should be able to fly both ways between cities
     * Therefore, the method adds a second copy of each flight to the list of valid flights, with source and dest. reversed
     * @param inputFileReader is a Reader designed to read in the Flight Data file
     * @return a formatted list of four-string lists, each representing one valid flight between two airports
     * @throws FileNotFoundException
     * @throws IOException
     * @throws DataFormatException
     */
    @Override
    public List<String[]> readFlightData(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException {
        //String to temporarily store the next line so it can be checked for non-nullness
        String nextLine;
        //String-array to store the current line as it is parsed
        String[] curLine;
        //List of String arrays that will eventually be returned
        List<String[]> retList = new ArrayList<>();
        //The reader, which iterates through the lines of the file and reads them into the list that is returned
        BufferedReader reader = new BufferedReader(inputFileReader);
        //Store the contents of the next line in the curLine string
        //Only continue if it is a) not null and b)not an empty line
        while((nextLine = reader.readLine()) != null && nextLine.length() > 0){
            curLine = nextLine.split(",");
            //Add curLine to the list of strings to return
            retList.add(curLine);
            //Also store a reversed version of each flight
            //The time and cost will be the same, but the source and destination will be reversed
            String tempCode = curLine[0];
            curLine[0] = curLine[1];
            curLine[1] = tempCode;
            retList.add(curLine);
        }
        return retList;
    }
}
// Author: ARCHIT THAKUR