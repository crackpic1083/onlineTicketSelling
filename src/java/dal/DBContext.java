package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author FPT University - PRJ301
 */
public class DBContext {
    protected Connection connection;
    public DBContext(){
        try {
            String user= "sa";
            String pass= "123";
            String url = "jdbc:sqlserver://DESKTOP-D3I3VKE\\SQLEXPRESS:1433;databaseName=AirlineBooking";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//Hàm load thu vi?n trong này lên 
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
