// --== CS400 File Header Information ==--
// Name: Joe Ciminski
// Email: ciminski@wisc.edu
// Team: Red
// Role: Data Wrangler
// TA: Xi Chen
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;

public interface AirportDataReaderInterface {
    public List<AirportInterface> readAirportData(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException;
    public List<String[]> readFlightData(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException;
}

