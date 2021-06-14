package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;



public class Passenger {
    String firstName;
    String lastName;
    String password;
    String passportNo;
    String phoneNo;
    String gender;
    String address;
    ArrayList<Flight> flights = new ArrayList<>();

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
            if (rs.next()) {
                System.out.println("Successful Login");
            } else {
                System.out.println("Successful Failed");
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
