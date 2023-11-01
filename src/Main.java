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

        //Initialize sentinel
        boolean done = false;
        //Menu
        do {
            System.out.println("Welcome to The House of Consultants");
            System.out.println("0. For exit");
            System.out.println("1. Office Worker");
            System.out.println("2. Consultant");

            String stringInput = in.next();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    OfficeWorkerMenu();
                    break;
                case "2":
                    ConsultantMenu();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        }while(!done);
        //Call menu
        /*OfficeWorkerMenu();
            ManageCustomerMenu();
                CreateCustomer();
                UpdateCustomer();
                SearchCustomer();
            ManageProjectMenu();
                CreateProject();
                UpdateProject();
                SearchProject();
                NotificationSystem();
                    GetNotification();
                    SendNotification();
            ManageConsultantMenu();
                CreateConsultant();
                UpdateConsultant();
                SearchConsultant();

        ConsultantMenu();
            ManageTaskMenu();
                CreateTask();
                UpdateTask();
                SearchTask();
*/

    }

    private static void SearchTask() {

    }

    private static void UpdateTask() {

    }

    private static void CreateTask() {

    }

    private static void ManageTaskMenu() {

    }

    private static void ConsultantMenu() {

    }

    private static void SearchConsultant() {

    }

    private static void UpdateConsultant() {

    }

    private static void CreateConsultant() {

    }

    private static void ManageConsultantMenu() {

    }

    private static void SendNotification() {

    }

    private static void GetNotification() {

    }

    private static void NotificationSystem() {

    }

    private static void SearchProject() {

    }

    private static void UpdateProject() {

    }

    private static void CreateProject() {

    }

    private static void ManageProjectMenu() {

    }

    private static void SearchCustomer() {

    }

    private static void UpdateCustomer() {

    }

    private static void CreateCustomer() {
    }

    private static void ManageCustomerMenu() {

    }

    private static void OfficeWorkerMenu() {

    }

}