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

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    OfficeWorkerMenu(connection);
                    break;
                case "2":
                    ConsultantMenu(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void SearchTask(Connection connection) {

    }

    private static void UpdateTask(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("UPDATE tblTask set fldTaskDescription='Test' where fldTaskId=1");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void CreateTask(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblTask(fldTaskDescription,fldProjectId,fldConsultantId,fldEstimatedTimeInHours) values ('Create menu',1,2,2)");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void ManageTaskMenu(Connection connection) throws SQLException {
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

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateTask(connection);
                    break;
                case "2":
                    SearchTask(connection);
                    break;
                case "3":
                    UpdateTask(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void ConsultantMenu(Connection connection) throws SQLException {
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

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    ManageTaskMenu(connection);
                    break;
                case "2":
                    NotificationSystem(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void SearchConsultant(Connection connection) {

    }

    private static void UpdateConsultant(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Update Consultant:");
            System.out.println("0. For exit");
            System.out.println("1. Update Consultant Name");
            System.out.println("2. Update Consultant Email");

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    ManageTaskMenu(connection);
                    break;
                case "2":
                    NotificationSystem(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }
            PreparedStatement preparedStatement = connection.prepareCall("UPDATE tblConsultant set fldConsultantName='Test' where fldConsultantId=1");
            preparedStatement.executeUpdate();
            System.out.println("Done");
        } while (!done);
    }

    private static void CreateConsultant(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblConsultant(fldConsultantName) values ('Simon')");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void ManageConsultantMenu(Connection connection) throws SQLException {
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

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateConsultant(connection);
                    break;
                case "2":
                    SearchConsultant(connection);
                    break;
                case "3":
                    UpdateConsultant(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void SendNotification(Connection connection) {

    }

    private static void GetNotification(Connection connection) {

    }

    private static void NotificationSystem(Connection connection) {

    }

    private static void SearchProject(Connection connection) {

    }

    private static void UpdateProject(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("UPDATE tblProject set fldProjectDescription='Test' where fldProjectId=1");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void CreateProject(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblProject(fldProjectDescription,fldCostumerId,fldEstimatedProjectTimeInDays,fldStatusId) values ('Bugfixes',1,30,1)");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void ManageProjectMenu(Connection connection) throws SQLException {
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

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateProject(connection);
                    break;
                case "2":
                    SearchProject(connection);
                    break;
                case "3":
                    UpdateProject(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }
//Programmet fortsætter til Consultant menu efter listen er printet
    private static void SearchCustomer(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM tblCustomer");
        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSetMetaData rsmd = resultSet.getMetaData();

        int columnSize = rsmd.getColumnCount();

        while (resultSet.next()) {
            System.out.println("-------------------------------------------------");
            for (int i = 1; i <= columnSize; i++) {
                switch (i) {
                    case 1:
                        System.out.println("Customer ID: " + resultSet.getString(i));
                        break;
                    case 2:
                        System.out.println("Name of Customer: " + resultSet.getString(i));
                        break;
                    case 3:
                        System.out.println("Phone number: " + resultSet.getString(i));
                        break;
                    case 4:
                        System.out.println("Email: " + resultSet.getString(i));
                        break;
                }
            }
            System.out.println("-------------------------------------------------");
        }
    }
    private static void UpdateCustomer(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("UPDATE tblCostumer set fldCustomerName='Test' where fldCustomerId=1");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void CreateCustomer(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblCustomer(fldCustomerName,fldCustomerPhoneNumber,fldCustomerEmail) values ('Linak','86803611','info@Linak.dk')");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void ManageCustomerMenu(Connection connection) throws SQLException {
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

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateCustomer(connection);
                    break;
                case "2":
                    SearchCustomer(connection);
                    break;
                case "3":
                    UpdateCustomer(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void OfficeWorkerMenu(Connection connection) throws SQLException {
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

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    ManageCustomerMenu(connection);
                    break;
                case "2":
                    ManageProjectMenu(connection);
                    break;
                case "3":
                    ManageConsultantMenu(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

}