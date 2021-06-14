package sample;

import java.util.ArrayList;
import java.util.Date;

public class Flight {
    String flightID;
    String source;
    String destination;
    int amount;
    Date date;
    String depTime;
    String arriTime;
    public Flight(String flightID, String source, String destination, int amount, Date date, String depTime, String arriTime) {
        this.flightID = flightID;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.date = date;
        this.depTime = depTime;
        this.arriTime = arriTime;
    }




}
