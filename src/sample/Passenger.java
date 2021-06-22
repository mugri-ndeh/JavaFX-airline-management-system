package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;



public class Passenger {
    int id;
    String first_name;
    String last_name;
    String username;
    String pass_word;
    String passport_num;
    int phone_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getPassport_num() {
        return passport_num;
    }

    public void setPassport_num(String passport_num) {
        this.passport_num = passport_num;
    }

    public int getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(int phone_num) {
        this.phone_num = phone_num;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    String gender;
    String address;
    String flight_id;
//    ArrayList<Flight> flights = new ArrayList<>();

    public Passenger(int id, String first_name, String last_name, String username, String pass_word, String passport_num, int phone_num, String gender, String address, String flight_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.pass_word = pass_word;
        this.passport_num = passport_num;
        this.phone_num = phone_num;
        this.gender = gender;
        this.address = address;
        this.flight_id = flight_id;
    }
    public Passenger(){}

    public Passenger(String username, String pass_word) {
        this.username = username;
        this.pass_word = pass_word;
    }

    Connection conn;


    boolean createAccount(String firstName, String lastName, String password, String passportNo, String phoneNo, String gender, String address, String userName){

        try{
            conn = new Driver().getConnection();
            System.out.println(conn);
            String sql = " INSERT INTO \"Users\" (\"firstName\", \"lastName\", \"password\", \"passportNo\", \"phoneNo\", \"gender\", \"address\", \"userName\")"
                    + " Values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,firstName);
            query.setString(2,lastName);
            query.setString(3,password);
            query.setString(4,passportNo);
            query.setString(5,phoneNo);
            query.setString(6,gender);
            query.setString(7,address);
            query.setString(8,userName);

            query.executeUpdate();
            conn.close();
            System.out.println(conn);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            e.getMessage();
            return false;
        }
        catch (Exception e){
            System.out.println("This is caused by passenger class");
            return false;
        }



    }
    boolean login(String username, String password){
        try{
            conn = new Driver().getConnection();
            System.out.println("Successful connection");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"Users\" WHERE \"userName\" = ? AND \"password\" = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);

            if (rs.next()) {
                System.out.println("Successful Login");
            } else {
                System.out.println("Login Failed");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    void logout(){}

    boolean bookFlight(String source, String destination, int amount, Date date, String depTime, String arriTime){

        System.out.println(conn);
        String sql = " INSERT INTO \"Flights\" (\"source\", \"destination\", \"_date\", \"departure_time\", \"arrivalTime\", \"cost\")"
                + " Values (?, ?, ?, ?, ?, ?)";
        try{

            conn = new Driver().getConnection();

            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,source);
            query.setString(2,destination);
            query.setDate(3, (java.sql.Date) date);
            query.setString(4,depTime);
            query.setString(5,arriTime);
            query.setInt(6,amount);


            query.executeUpdate();
            conn.close();
            System.out.println(conn);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
