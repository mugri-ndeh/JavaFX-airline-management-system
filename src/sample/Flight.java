package sample;

import java.util.ArrayList;
import java.util.Date;

public class Flight {
    String flight_id;
    String source;
    String destination;
    Date _date;
    String departure_time;
    String arrival_time;
    int num_of_seats;
    int cost;

    public Flight(String flight_id, String source, String destination, Date _date, String departure_time, String arrival_time, int num_of_seats, int cost) {
        this.flight_id = flight_id;
        this.source = source;
        this.destination = destination;
        this._date = _date;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.num_of_seats = num_of_seats;
        this.cost = cost;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getNum_of_seats() {
        return num_of_seats;
    }

    public void setNum_of_seats(int num_of_seats) {
        this.num_of_seats = num_of_seats;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
