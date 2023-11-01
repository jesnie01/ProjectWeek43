import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Connect to SQL
        Properties properties = new Properties();
        properties.setProperty("user", "sa");
        properties.setProperty("password", "1234");
        properties.setProperty("encrypt", "false");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=dbWeek43";
        Connection connection = DriverManager.getConnection(url, properties);

        //Initialize Scanner
        Scanner in = new Scanner(System.in);

        //Menu
        System.out.println("Welcome to The House of Consultants");
        System.out.println("0. For exit");
        System.out.println("1. Office Worker");
        System.out.println("2. Consultant");

        //Validate input


    }

}