package sample;

public class Booked {
    String username;
    String flight_id;
    int num_of_seats;

    public Booked(String username, String flight_id, int num_of_seats) {
        this.username = username;
        this.flight_id = flight_id;
        this.num_of_seats = num_of_seats;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public int getNum_of_seats() {
        return num_of_seats;
    }

    public void setNum_of_seats(int num_of_seats) {
        this.num_of_seats = num_of_seats;
    }

}
