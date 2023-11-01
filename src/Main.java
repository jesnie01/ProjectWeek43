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

        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";

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
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void SearchTask() {

    }

    private static void UpdateTask() {

    }

    private static void CreateTask() {

    }

    private static void ManageTaskMenu() {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Consultant Menu:");
            System.out.println("0. For exit");
            System.out.println("1. Create Task");
            System.out.println("2. Search Task");
            System.out.println("3. Update Task");

            String stringInput = in.next();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateTask();
                    break;
                case "2":
                    SearchTask();
                    break;
                case "3":
                    UpdateTask();
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void ConsultantMenu() {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Consultant Menu:");
            System.out.println("0. For exit");
            System.out.println("1. Manage Task");
            System.out.println("2. Notifications");

            String stringInput = in.next();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    ManageTaskMenu();
                    break;
                case "2":
                    NotificationSystem();
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void SearchConsultant() {

    }

    private static void UpdateConsultant() {

    }

    private static void CreateConsultant() {

    }

    private static void ManageConsultantMenu() {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Consultant Menu:");
            System.out.println("0. For exit");
            System.out.println("1. Create Consultant");
            System.out.println("2. Search Consultant");
            System.out.println("3. Update Consultant");

            String stringInput = in.next();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateConsultant();
                    break;
                case "2":
                    SearchConsultant();
                    break;
                case "3":
                    UpdateConsultant();
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
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
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Consultant Menu:");
            System.out.println("0. For exit");
            System.out.println("1. Create Project");
            System.out.println("2. Search Project");
            System.out.println("3. Update Project");

            String stringInput = in.next();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateProject();
                    break;
                case "2":
                    SearchProject();
                    break;
                case "3":
                    UpdateProject();
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void SearchCustomer() {

    }

    private static void UpdateCustomer() {

    }

    private static void CreateCustomer() {
    }

    private static void ManageCustomerMenu() {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Consultant Menu:");
            System.out.println("0. For exit");
            System.out.println("1. Create Customer");
            System.out.println("2. Search Customer");
            System.out.println("3. Update Customer");

            String stringInput = in.next();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateCustomer();
                    break;
                case "2":
                    SearchCustomer();
                    break;
                case "3":
                    UpdateCustomer();
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void OfficeWorkerMenu() {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize Sentinel
        boolean done = false;
        do {
            System.out.println("Office Worker Menu:");
            System.out.println("0. For exit");
            System.out.println("1. Manage Customer");
            System.out.println("2. Manage Project");
            System.out.println("3. Manage Consultant");

            String stringInput = in.next();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    ManageCustomerMenu();
                    break;
                case "2":
                    ManageProjectMenu();
                    break;
                case "3":
                    ManageConsultantMenu();
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

}