// This is an implementation of AirportInterface by the Airport class. 

public class Airport implements AirportInterface {
    String name;
    String code;
    String location;

    public Airport(String name, String code, String location){
        this.name = name;
        this.code = code;
        this.location = location;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public boolean equals(AirportInterface airport) {
        return airport.getCode().equals(this.code);
    }
}
// Author: ARCHIT THAKUR
