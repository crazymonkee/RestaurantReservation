import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = init();
        if (conn != null) {
            runApplication(conn);
        }
    }

    private static Connection init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restomanagement", "root", "");
            if (conn != null) {
                System.out.println("Connection established");
                return conn;
            } else {
                System.out.println("Cannot connect to the database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void runApplication(Connection conn) {
        Scanner sc = new Scanner(System.in);
        Order orderInstance = new Order(conn); // Create a single instance of Order

        int choice;
        do {
            choice = menu(sc);
            switch (choice) {
                case 1:
                    new Restaurant(conn).showMenu();
                    break;
                case 2:
                    new Reservation(conn).makeReservation(sc);
                    break;
                case 3:
                    orderInstance.orderFood(sc);
                    break;
                case 4:
                    orderInstance.checkOut();
                    break;
                case 5:
                    new MenuManager(conn).manageMenu(sc);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);

        sc.close();
    }

    private static int menu(Scanner sc) {
        System.out.println("Laper Restaurant Reservation");
        System.out.println("1. Show Menu");
        System.out.println("2. Make Reservation");
        System.out.println("3. Order Food");
        System.out.println("4. Check Out");
        System.out.println("5. Manage Menu");
        System.out.println("6. Exit");
        System.out.println("Input your choice");
        return sc.nextInt();
    }
}
