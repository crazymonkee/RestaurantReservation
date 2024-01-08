import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private Connection conn;
    private ArrayList<String> orderName = new ArrayList<>();
    private int total = 0;

    public Order(Connection conn) {
        this.conn = conn;
    }

    public void orderFood(Scanner sc) {
        try {
            System.out.println("Enter Menu ID:");
            int menuID = sc.nextInt();
            sc.nextLine();

            String query = "SELECT * FROM menu WHERE MenuID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, menuID);
                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("No menu found with the given ID.");
                } else {
                    while (rs.next()) {
                        System.out.println("Nama menu: " + rs.getString("NamaMenu") +
                                " , Harga: " + rs.getDouble("Harga") +
                                " , Resto Cabang: " + rs.getString("RestoCabang") +
                                ", Jenis Menu: " + rs.getString("JenisMenu") +
                                ", Narasi:" + rs.getString("Narasi"));
                        this.total += rs.getDouble("Harga");                       
                        orderName.add(rs.getString("NamaMenu"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkOut() {
        System.out.println("Ordered Items:");
        for (String itemName : orderName) {
            System.out.println(itemName);
        }
        System.out.println("Total Amount: " +total);
        orderName.clear();
        total = 0;
    }
}
