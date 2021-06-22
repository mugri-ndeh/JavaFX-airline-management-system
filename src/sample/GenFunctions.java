package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GenFunctions {
    Connection conn;

    boolean usernameExists(String username){
        try{
            conn = new Driver().getConnection();
            String sql = "SELECT * FROM user_tbl WHERE username = ?";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,username);
            ResultSet rs = query.executeQuery();
            return rs.next();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    boolean emptyFields(String username, String password){
        return username.isEmpty() || password.isEmpty();
    }
    boolean emptyFields(String firstname, String lastname, String username, String password, String passportNo, int phoneNo, String gender, String address){
        return firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty() || passportNo.isEmpty() || String.valueOf(phoneNo).isEmpty()|| gender.isEmpty() || address.isEmpty() ;
    }
    boolean weakPassword(String password){
        return password.length() < 5;
    }

}
