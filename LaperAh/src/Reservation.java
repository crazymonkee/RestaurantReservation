import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Reservation {
    private Connection conn;

    public Reservation(Connection conn) {
        this.conn = conn;
    }

    public void makeReservation(Scanner sc) {
        try {
            System.out.println("Enter customer name:");
            String customerName = sc.next();
            sc.nextLine();
            System.out.println("Enter Resto Branch:");
            String branch = sc.next();
            sc.nextLine();
            System.out.println("Enter table type (Romantic/General/Family)[Case Sensitive]:");
            String tableType = sc.next();
            sc.nextLine();
            System.out.println("Enter Reserve Status (InOrder/InReserve/Finalized):");
            String reservationStatus = sc.next();
            sc.nextLine();
            System.out.println("Enter Reserve Date (YYYY-MM-DD):");
            String reservationDate = sc.next();
            sc.nextLine();
            System.out.println("Enter number of people:");
            int peopleNumber = sc.nextInt();
            sc.nextLine();

            // Validate if the table type is valid (Romantic, General, or Family)
            if (!tableType.equals("Romantic") && !tableType.equals("General") && !tableType.equals("Family")) {
                System.out.println("Invalid table type");
                return;
            }

            // Check if the number of people is within the allowed limit for the table type
            int maxPeople;
            switch (tableType) {
                case "Romantic":
                    maxPeople = 2;
                    break;
                case "General":
                    maxPeople = 4;
                    break;
                case "Family":
                    maxPeople = 10;
                    break;
                default:
                    maxPeople = 0; // Invalid table type
            }

            if (peopleNumber > maxPeople) {
                System.out.println("Number of people exceeds the maximum limit for " + tableType + " tables");
                return;
            }

            // Insert into the database
            String insertReservationQuery = "INSERT INTO reservasi (NamaPemesan, RestoCabang, Status, TanggalReservasi) VALUES (?, ?, ?, ?)";
            String insertDetailReservationQuery = "INSERT INTO detailreservasi (TipeMeja, JumlahOrang) VALUES (?, ?)";

            try (PreparedStatement pstReservation = conn.prepareStatement(insertReservationQuery);
                 PreparedStatement pstDetailReservation = conn.prepareStatement(insertDetailReservationQuery)) {

                pstReservation.setString(1, customerName);
                pstReservation.setString(2, branch);
                pstReservation.setString(3, reservationStatus);
                pstReservation.setString(4, reservationDate);

                int rowsAffectedReservation = pstReservation.executeUpdate();

                pstDetailReservation.setString(1, tableType);
                pstDetailReservation.setInt(2, peopleNumber);

                int rowsAffectedDetailReservation = pstDetailReservation.executeUpdate();

                if (rowsAffectedReservation > 0 && rowsAffectedDetailReservation > 0) {
                    System.out.println("Reservation made successfully");
                } else {
                    System.out.println("Failed to make reservation");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
