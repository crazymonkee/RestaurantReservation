import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Restaurant {
    private Connection conn;

    public Restaurant(Connection conn) {
        this.conn = conn;
    }

    public void showMenu() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Menu");
            System.out.println("Menu:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("MenuID") +
                        ", Name: " + rs.getString("NamaMenu") +
                        ", Price: " + rs.getDouble("Harga") +
                        ", Branch: " + rs.getString("RestoCabang") +
                        ", Menu Type: " + rs.getString("JenisMenu") +
                        ", Details: " + rs.getString("Narasi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
