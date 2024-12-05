package com.ExpenseTracker.modelos.principales;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

    private static final String URL="jdbc:mysql://localhost:3306/ExpenseTrackerDB";
    private static final String USER="root";
    private static final String PASSWORD="Guerra2001+";

   private static Connection connection=null;

   //method to obtain the connection

    public static Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
               // System.out.println("Connected to the database seccessfully");
            }

            }catch(SQLException e){
                System.out.println("Database connection failed: " + e.getMessage());
            }

            return connection;


    }
public static void closeConnection (){
        if (connection !=null){
            try{
                connection.close();
                System.out.println(" Disconnected from the Database");
            }catch (SQLException e){
                System.out.println("failed to Disconnected: " + e.getMessage());
            }
        }
}
}
