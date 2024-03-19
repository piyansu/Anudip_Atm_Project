import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bank Management System!");
        System.out.println("Select an option:");
        System.out.println("1. New Registration");
        System.out.println("2. Login");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        Connection connection = null;
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            connection = connectionManager.getConnection();

            switch (choice) {
                case 1:
                    System.out.println("New Registration:");
                    registerUser(connection);
                    break;
                case 2:
                    System.out.println("Login:");
                    loginUser(connection);
                    break;
                default:
                    System.out.println("Invalid choice. Exiting...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void registerUser(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        
        // Account type selection
        System.out.println("Account Types:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        System.out.print("Enter Account Type (1 for Savings, 2 for Current): ");
        int accountTypeChoice = scanner.nextInt();
        String accountType = (accountTypeChoice == 1) ? "Savings" : "Current";

        LocalDate currentDate = LocalDate.now();
        // Convert LocalDate to string
        String dateString = currentDate.toString();
        
        // Insert user details into the database
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO customers (FirstName, LastName, Address, Email) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, email);
            statement.executeUpdate();
        }
        
        // Insert account details into the database
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO accounts (AccountType, OpeningDate) VALUES (?, ?)")) {
            statement.setString(1, accountType);
            statement.setString(2, dateString);
            statement.executeUpdate();
        }

        System.out.println("\nUser registration successful!");
    }


    private static void loginUser(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM customers WHERE Email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\nUser login successful!");
                System.out.println("Welcome back, " + email + "!");
            } else {
                System.out.println("\nUser not found. Please register first.");
            }
        }
    }
}
