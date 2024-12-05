package com.ExpenseTracker.modelos;

import com.ExpenseTracker.modelos.principales.ConnectionBD;

import java.sql.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ExpenseManager {



    private List<Expense> expenses;
    private int nextId;

    public ExpenseManager(){
        this.expenses= new ArrayList<>();
        this.nextId=1;
    }

    // method for adding expres
    public void addExpense(String description, double amount, LocalDate date){
        String query= "INSERT INTO expenses (description, amount, date) VALUES (?,?,?)";

        try(Connection connection =ConnectionBD.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,description);
            statement.setDouble(2,amount);
            statement.setDate(3, Date.valueOf(date));
            statement.executeUpdate();
            System.out.println("expense adding successfully");



            } catch (SQLException e) {
            System.out.println("error adding Expense to database " + e.getMessage());
        }

    }


    //method for update expense

    public void updateExpense(int id, String description, double amount, Date date){
        String query ="UPDATE expenses SET description = ?, amount = ?, date = ? WHERE id = ?"
        ;

        try(Connection connection = ConnectionBD.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){


            statement.setString(1,description);
            statement.setDouble(2,amount);
            statement.setDate(3,date);
            statement.setInt(4,id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected >0){
                System.out.println("Expense update successfully ");
            }else{
                System.out.println( "Expense with ID: "+ id + " not found ");
            }


        }catch (SQLException e){
            System.out.println( "error update expense: "+ e.getMessage());

        }
    }
    //method for delet Expense
    public void removeExpense(int id){
        String query = " DELETE FROM expenses WHERE id = ? ";

        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1,id);

            int rowsAffected= statement.executeUpdate();
            if (rowsAffected > 0 ){
                System.out.println("Expense delet successfully");

            }else{
                System.out.println( "Expense with ID: " + id + " not found");
            }

        }catch (SQLException e){
            System.out.println(" error delet expense: " + e.getMessage());

        }
    }

    //metho to format amount as currency
    public String formatAmount(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }

    //method for list expense
    public void listExpense(){
        String query= "SELECT * FROM expenses";

        try(Connection connection= ConnectionBD.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("ID\tDescription\tAmount\tDate");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");

               String formattedAmount = formatAmount(amount);
                System.out.println(id + "\t" + description +  "\t" + formattedAmount +"\t" + date);

            }
        }catch (SQLException e){
                System.out.println("Error listing Expenses: " + e.getMessage());

        }



    }

}
