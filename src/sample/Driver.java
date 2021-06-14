package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
    Connection conn;

   public Connection getConnection(){
        String user = "postgres";
        String password = "password";
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/airline";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out .println("Successfully connected to postgres");
        }
        catch (ClassNotFoundException e){
            System.out.println("JDBC is trash");
        }
        catch (SQLException ex) {
            System.out.println("Conn "+conn);
            System.out .println("An error occurred while connecting postgres");

        } catch (Exception e){
            System.out.println("Exception");
        }

        return conn;
    }


}
