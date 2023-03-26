/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;
/**
 *
 * @author hoain
 */
public class MyConnection {
    private static final String username = "root";
    private static final String password = "";
//    private static final String dataConn = "jdbc:mysql://localhost:3306/student_manager";
    private static final String dataName = "student_manager";
    private static Connection con = null;
    
    private static Connection openConnector(String DB_name, String DB_user, String DB_pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        String urlDB = "jdbc:mysql://localhost:3306/" + DB_name;
        try {
            return DriverManager.getConnection(urlDB, DB_user, DB_pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Connection getConnection() {
        return openConnector(dataName, username, password);
    }
            
}
