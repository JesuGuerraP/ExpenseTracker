package com.ExpenseTracker.modelos.principales;

import com.ExpenseTracker.modelos.ExpenseManager;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainConExeption {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ExpenseManager expenseManager = new ExpenseManager();

            // Use try-with-resources for database connection
            try {
                ConnectionBD.getConnection();
                processMenu(scanner, expenseManager);
            } catch (Exception e) {
                System.out.println("Error connecting to database: " + e.getMessage());
            } finally {
                ConnectionBD.closeConnection();
            }
        }
    }

    private static void processMenu(Scanner scanner, ExpenseManager expenseManager) {
        boolean running = true;

        while (running) {
            try {
                displayMenu();
                int choice = getValidChoice(scanner);

                switch (choice) {
                    case 1 -> addExpenses(scanner, expenseManager);
                    case 2 -> expenseManager.listExpense();
                    case 3 -> updateExpense(scanner, expenseManager);
                    case 4 -> deleteExpense(scanner, expenseManager);
                    case 5 -> {
                        System.out.println("Exiting Expense Tracker. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid Option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nExpense Tracker Menu:");
        System.out.println("1. Add Expense");
        System.out.println("2. List Expenses");
        System.out.println("3. Update Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getValidChoice(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    // Method to add expenses with validation
    public static void addExpenses(Scanner scanner, ExpenseManager expenseManager) {
        scanner.nextLine(); // Consume any leftover newline

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        double amount = getValidAmount(scanner, "Enter Amount: ");
        LocalDate date = getValidDate(scanner, "Enter Date (yyyy-mm-dd): ");

        expenseManager.addExpense(description, amount, date);
        System.out.println("Expense added successfully.");
    }

    // Update expense with improved validation
    public static void updateExpense(Scanner scanner, ExpenseManager expenseManager) {
        System.out.print("Enter Expense ID to update: ");
        int id = getValidId(scanner);
        scanner.nextLine(); // Consume newline

        System.out.print("New Description: ");
        String newDescription = scanner.nextLine().trim();

        double newAmount = getValidAmount(scanner, "Enter New Amount: ");
        LocalDate newDate = getValidDate(scanner, "Enter New Date (yyyy-mm-dd): ");

        try {
            expenseManager.updateExpense(id, newDescription, newAmount, Date.valueOf(newDate));
            System.out.println("Expense updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating expense: " + e.getMessage());
        }
    }

    // Delete expense with improved validation
    public static void deleteExpense(Scanner scanner, ExpenseManager expenseManager) {
        System.out.print("Enter Expense ID to delete: ");
        int id = getValidId(scanner);

        try {
            expenseManager.removeExpense(id);
            System.out.println("Expense deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting expense: " + e.getMessage());
        }
    }

    // Utility method to get a valid positive amount
    private static double getValidAmount(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double amount = Double.parseDouble(scanner.nextLine().trim());

                if (amount < 0) {
                    System.out.println("Amount must be positive. Please try again.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    // Utility method to get a valid date
    private static LocalDate getValidDate(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
            }
        }
    }

    // Utility method to get a valid ID
    private static int getValidId(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric ID.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}