import java.sql.*;
import java.util.ArrayList;
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
            System.out.println("What is your role?");
            System.out.println("1. Office Worker");
            System.out.println("2. Consultant");
            System.out.println("0. To quit program");

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    System.out.println("Thank you for using our program");
                    done = true;
                    break;
                case "1":
                    OfficeWorkerMenu(connection);
                    break;
                case "2":
                    //ConsultantMenu(connection);
                    PickConsultant(connection);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }

        }while(!done);
    }

    private static void PickConsultant(Connection connection) throws SQLException {
        System.out.println("Pick Consultant:");
        PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM tblConsultant");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Integer> conID = new ArrayList<>();
        ArrayList<String> conName = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";

        while(resultSet.next())
        {
            System.out.printf("%s. %s%n", resultSet.getString(1), (resultSet.getString(2)));
            conID.add(resultSet.getInt(1));
            conName.add(resultSet.getString(2));

        }
        //System.out.printf("%s%n%s",cID,cName);
        while(!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid choice");
            for (int i = 0; i < conID.size(); i++) {
                System.out.printf("%s. %s%n",conID.get(i),conName.get(i));
            }
        }
        int conChoice = input.nextInt();
        //updateDB(connection,choice,cID,cName);

        boolean isDone = false;
        do {
            preparedStatement = connection.prepareCall("SELECT * from tblConsultant where fldConsultantId=" + conChoice);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();

            int columnSize = rsmd.getColumnCount();

            while (resultSet.next()) {

                for (int i = 1; i <= columnSize; i++) {
                    switch (i) {
                        case 1:
                            System.out.printf("ID of Consultant: %s%n", resultSet.getString(i));
                            break;
                        case 2:
                            System.out.printf("Name of Consultant: %s%n", resultSet.getString(i));
                            break;
                        case 3:
                            System.out.printf("CPR Number: %s%n",resultSet.getString(i));
                            break;
                        case 4:
                            System.out.printf("Active: %s%n", resultSet.getString(i));
                            break;
                    }
                }
                //Prompt menu
                System.out.println("Choose option:");
                System.out.println("1. Manage Task");
                System.out.println("0. Go back");
            }
            //Choose menuoption
            input=new Scanner(System.in);
            String stringInput = input.nextLine();
            switch(stringInput){
                case "0":
                    isDone = true;
                    break;
                case "1":
                    ManageTaskMenu(connection, conChoice, conName);
                    break;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;

            }

            while(!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Not a valid choice");
            }
        }while(!isDone);
    }

    private static void SearchTask(Connection connection, int conChoice, ArrayList<String> conName) throws SQLException {
        System.out.printf("All %s's tasks:%n", conName.get(conChoice-1));
        PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM tblTask WHERE fldConsultantId ='" + conChoice + "' AND fldActiveId =1");
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();

        int columnSiz = rsmd.getColumnCount();

        Scanner input = new Scanner(System.in);

        while (resultSet.next()) {
            for (int i = 1; i <= columnSiz; i++) {
                System.out.println(rsmd.getColumnLabel(i) + ": " + resultSet.getString(i));
            }
            System.out.println("------------------------");
        }
    }

    private static void UpdateTask(Connection connection, int conChoice, ArrayList<String> conName) throws SQLException {

        PreparedStatement preparedStatement;
        Scanner input = new Scanner(System.in);

        SearchTask(connection, conChoice, conName);
        System.out.println("Choose TaskId:");
        boolean done = false;

        while(!done) {
            while(!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Not a valid choice");
                SearchTask(connection, conChoice, conName);
                System.out.println("Choose TaskId:");
            }
            //If input is int, save input
            int taskId = input.nextInt();


            System.out.printf("How many minutes did you work on Task ID %d:%n",taskId);
            while(!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Not a valid choice");
                System.out.println("How many minutes:");
            }
            //If input, save input
            int timeOnTask = input.nextInt();
            preparedStatement = connection.prepareCall("INSERT INTO tblTimeOnTask(fldConsultantId, fldTaskId, fldTimeSpentInMinutes) values (?,?,?)");
            preparedStatement.setInt(1,conChoice);
            preparedStatement.setInt(2,taskId);
            preparedStatement.setInt(3,timeOnTask);
            preparedStatement.executeUpdate();

            System.out.printf("You have worked %d minutes on TaskID %d!%n", timeOnTask, taskId);
            done = true;

        }
    }

    private static void CreateTask(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblTask(fldTaskDescription,fldProjectId,fldConsultantId,fldEstimatedTimeInHours) values ('Create menu',1,2,2)");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    private static void ManageTaskMenu(Connection connection, int conChoice, ArrayList<String> conName) throws SQLException {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Task Menu:");
            System.out.println("1. Create Task");
            System.out.println("2. Search Task");
            System.out.println("3. Update Task");
            System.out.println("0. Go back");

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    CreateTask(connection);
                    break;
                case "2":
                    SearchTask(connection, conChoice, conName);
                    break;
                case "3":
                    UpdateTask(connection, conChoice, conName);
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
            System.out.println("1. Manage Task");
            System.out.println("2. Notifications");
            System.out.println("0. Go back");

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    //ManageTaskMenu(connection);
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

    private static void SearchConsultant(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM tblConsultant");
        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSetMetaData rsmd = resultSet.getMetaData();

        int columnSize = rsmd.getColumnCount();

        while (resultSet.next()) {
            System.out.println("-------------------------------------------------");
            for (int i = 1; i <= columnSize; i++) {
                switch (i) {
                    case 1:
                        System.out.println("Consultant ID: " + resultSet.getString(i));
                        break;
                    case 2:
                        System.out.println("Name of Consultant: " + resultSet.getString(i));
                        break;
                    case 3:
                        System.out.println("CPR Number: " + resultSet.getString(i));
                        break;
                    case 4:
                        System.out.println("Active: "+ resultSet.getString(i));

                            PreparedStatement preparedStatement2 = connection.prepareCall("SELECT tblKnowledge.fldKnowledgeName FROM tblKnowledgeProfile JOIN tblKnowledge ON tblKnowledgeProfile.fldKnowledgeId = tblKnowledge.fldKnowledgeId  WHERE tblKnowledgeProfile.fldConsultantId=" + resultSet.getString(1));
                            ResultSet resultSet2 = preparedStatement2.executeQuery();

                            ResultSetMetaData rsmd2 = resultSet2.getMetaData();

                            int columnSizeKnowledge = rsmd2.getColumnCount();
                            System.out.print("Knowledge: ");
                            while (resultSet2.next()) {
                                System.out.print(resultSet2.getString(1) + ", ");
                            }
                        System.out.println();

                        break;

                }
            }
            System.out.println("-------------------------------------------------");
        }
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
            System.out.println("1. Update Consultant Name");
            System.out.println("2. Update Consultant Email");
            System.out.println("0. Go back");

            String stringInput = in.nextLine();
            switch (stringInput) {
                case "0":
                    done = true;
                    break;
                case "1":
                    //ManageTaskMenu(connection);
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
            System.out.println("0. Go back");
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

    /**
     * Function to Search already existing Projects
     * @param connection
     * @throws SQLException
     */
    private static void SearchProject(Connection connection) throws SQLException {

    }

    /**
     * Function to Update an already existing Project
     * @param connection
     * @throws SQLException
     */
    private static void UpdateProject(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("UPDATE tblProject set fldProjectDescription='Test' where fldProjectId=1");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    /**
     * Function to Create a new Project and add it to the database
     * @param connection
     * @throws SQLException
     */
    private static void CreateProject(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblProject(fldProjectDescription,fldCustomerId,fldEstimatedProjectTimeInDays,fldStatusId) values ('Bugfixes',1,30,1)");
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }

    /**
     * Menu for managing Projects (Create, Search or Update)
     * @param connection
     * @throws SQLException
     */
    private static void ManageProjectMenu(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        //Initialize red color
        String redColor = "\u001B[31m";
        //Reset text color to default
        String resetColor = "\u001B[0m";
        //Initialize sentinel
        boolean done = false;
        do {
            System.out.println("Project Menu:");
            System.out.println("0. Go back");
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

    /**
     * Function to search for existing records with Customers
     * @param connection
     * @throws SQLException
     */
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
                    case 5:
                        System.out.println("CVR: " + resultSet.getString(i));
                        break;
                    case 6:
                        System.out.println("Active: " + resultSet.getString(i));
                        break;
                }
            }
            System.out.println("-------------------------------------------------");
        }
    }
    private static void UpdateCustomer(Connection connection) throws SQLException {
        System.out.println("Update Customer Menu:");

        PreparedStatement preparedStatement = connection.prepareCall("SELECT fldCustomerId, fldCustomerName FROM tblCustomer");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Integer> cID = new ArrayList<>();
        ArrayList<String> cName = new ArrayList<>();

        Scanner input = new Scanner(System.in);


        while(resultSet.next())
        {
            System.out.printf("[%s] %s%n", resultSet.getString(1), (resultSet.getString(2)));
            cID.add(resultSet.getInt(1));
            cName.add(resultSet.getString(2));

        }
        //System.out.printf("%s%n%s",cID,cName);
        while(!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid choice");
            for (int i = 0; i < cID.size(); i++) {
                System.out.printf("[%s] %s%n",cID.get(i),cName.get(i));
            }
        }
        int choice = input.nextInt();
        //updateDB(connection,choice,cID,cName);

        boolean isDone = false;
        do {
            preparedStatement = connection.prepareCall("SELECT * from tblCustomer where fldCustomerId=" + choice);
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();

            int columnSize = rsmd.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnSize; i++) {
                    switch (i) {
                        case 2:
                            System.out.printf("%s. Name of Customer: %s%n", i - 1, resultSet.getString(i));
                            break;
                        case 3:
                            System.out.printf("%s. Phone number: %s%n", i - 1, resultSet.getString(i));
                            break;
                        case 4:
                            System.out.printf("%s. Email: %s%n", i - 1, resultSet.getString(i));
                            break;
                        case 5:
                            System.out.printf("%s. CVR Number: %s%n", i - 1, resultSet.getString(i));
                            break;
                    }
                }
                System.out.println("0. Go back");
            }
            while(!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Not a valid choice");
            }
            int tempChoice = input.nextInt();


            switch(tempChoice){
                case 0:
                    isDone = true;
                    break;
                case 1: {
                    System.out.printf("Please type in the new name for %s:\n",cName.get(choice-1));
                    //Scanner in = new Scanner(System.in);
                    input = new Scanner(System.in);
                    String nameChange = input.nextLine();
                    preparedStatement = connection.prepareCall("UPDATE tblCustomer set fldCustomerName ='" + nameChange + "' WHERE fldCustomerId =" + choice);
                    preparedStatement.executeUpdate();
                    break;
                }
                case 2:
                    System.out.printf("Please type in a new phone number for %s:\n",cName.get(choice-1));
                    input = new Scanner(System.in);
                    String phoneChange = input.nextLine();
                    preparedStatement = connection.prepareCall("UPDATE tblCustomer set fldCustomerPhoneNumber ='" + phoneChange + "' WHERE fldCustomerId =" + choice);
                    preparedStatement.executeUpdate();
                    break;
                case 3:
                    System.out.printf("Please type in a new Email for %s:\n",cName.get(choice-1));
                    input = new Scanner(System.in);
                    String emailChange = input.nextLine();
                    preparedStatement = connection.prepareCall("UPDATE tblCustomer set fldCustomerEmail ='" + emailChange + "' WHERE fldCustomerId =" + choice);
                    preparedStatement.executeUpdate();
                    break;
                case 4:
                    System.out.printf("Please type in a new CVR for %s:\n",cName.get(choice-1));
                    input = new Scanner(System.in);
                    String cvrChange = input.nextLine();
                    preparedStatement = connection.prepareCall("UPDATE tblCustomer set fldCVR ='" + cvrChange + "' WHERE fldCustomerId =" + choice);
                    preparedStatement.executeUpdate();
                    break;
            }
        }while(!isDone);
    }

    private static void CreateCustomer(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        // ANSI escape code for bright green text
        String brightGreenColor = "\u001B[92m";

        // ANSI escape code for resetting text color
        String resetColor = "\u001B[0m";

        //Enter new customer information (Name, Phone number, Email, CVR number)
        System.out.println("Please enter the Customer Name:");
        String customerName = in.nextLine();
        System.out.println("Please enter the Customer Phone Number:");
        String customerPhoneNumber = in.nextLine();
        System.out.println("Please enter the Customer Email:");
        String customerEmail = in.nextLine();
        System.out.println("Please enter the Customer CVR Number:");
        String customerCVR = in.nextLine();
        PreparedStatement preparedStatement = connection.prepareCall("INSERT INTO tblCustomer(fldCustomerName,fldCustomerPhoneNumber,fldCustomerEmail,fldCVR, fldActiveId) values (?,?,?,?,?)");
        preparedStatement.setString(1,customerName);
        preparedStatement.setString(2,customerPhoneNumber);
        preparedStatement.setString(3,customerEmail);
        preparedStatement.setString(4,customerCVR);
        preparedStatement.setInt(5,1);

        preparedStatement.executeUpdate();

        //Prompt to user about input
        System.out.println(brightGreenColor+"You have entered the following:");
        System.out.println("Customer Name, Phone Number, Email, CVR:");
        System.out.printf("%s, %s, %s, %s\n",customerName, customerPhoneNumber, customerEmail, customerCVR+resetColor);
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
            System.out.println("Customer Menu:");
            System.out.println("0. Go back");
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
            System.out.println("0. Go back");
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