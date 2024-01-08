import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuManager {
    private Connection conn;

    public MenuManager(Connection conn) {
        this.conn = conn;
    }

    public void manageMenu(Scanner sc) {
        try {
            System.out.println("1. Add Menu");
            System.out.println("2. Update Menu");
            System.out.println("3. Delete Menu");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    // Add Menu
                    System.out.println("Enter menu name:");
                    String menuName = sc.next();
                    sc.nextLine();
                    System.out.println("Enter menu price:");
                    double menuPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter Branch:");
                    String branch = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Menu Type (Regular/Special/Local Special):");
                    String menuType = sc.next();
                    sc.nextLine();
                    System.out.println("Enter Details:");
                    String details = sc.next();
                    sc.nextLine();

                    PreparedStatement addMenuPst = conn.prepareStatement("INSERT INTO Menu (NamaMenu, Harga, RestoCabang, JenisMenu, Narasi) VALUES (?, ?, ?, ?, ?)");
                    addMenuPst.setString(1, menuName);
                    addMenuPst.setDouble(2, menuPrice);
                    addMenuPst.setString(3, branch);
                    addMenuPst.setString(4, menuType);
                    addMenuPst.setString(5, details);

                    int addMenuRowsAffected = addMenuPst.executeUpdate();

                    if (addMenuRowsAffected > 0) {
                        System.out.println("Menu added successfully");
                    } else {
                        System.out.println("Failed to add menu");
                    }
                    break;

                case 2:
                    // Update Menu
                    System.out.println("Enter menu ID:");
                    int menuId = sc.nextInt();
                    System.out.println("Enter new menu name:");
                    String newMenuName = sc.next();
                    System.out.println("Enter new menu price:");
                    double newMenuPrice = sc.nextDouble();

                    // Check if the menu ID exists
                    if (!checkMenuExists(menuId)) {
                        System.out.println("Invalid menu ID");
                        return;
                    }

                    // Check if the menu can be updated (not ordered by any reservation)
                    if (!checkMenuCanBeUpdated(menuId)) {
                        System.out.println("Menu cannot be updated as it is already ordered by a reservation");
                        return;
                    }

                    PreparedStatement updateMenuPst = conn.prepareStatement("UPDATE menu SET NamaMenu = ?, Harga = ? WHERE MenuID = ?");
                    updateMenuPst.setString(1, newMenuName);
                    updateMenuPst.setDouble(2, newMenuPrice);
                    updateMenuPst.setInt(3, menuId);

                    int updateMenuRowsAffected = updateMenuPst.executeUpdate();

                    if (updateMenuRowsAffected > 0) {
                        System.out.println("Menu updated successfully");
                    } else {
                        System.out.println("Failed to update menu");
                    }
                    break;

                case 3:
                    // Delete Menu
                    System.out.println("Enter menu ID:");
                    int deleteMenuId = sc.nextInt();

                    // Check if the menu ID exists
                    if (!checkMenuExists(deleteMenuId)) {
                        System.out.println("Invalid menu ID");
                        return;
                    }

                    // Check if the menu can be deleted (not ordered by any reservation)
                    if (!checkMenuCanBeDeleted(deleteMenuId)) {
                        System.out.println("Menu cannot be deleted as it is already ordered by a reservation");
                        return;
                    }

                    PreparedStatement deleteMenuPst = conn.prepareStatement("DELETE FROM menu WHERE MenuID = ?");
                    deleteMenuPst.setInt(1, deleteMenuId);

                    int deleteMenuRowsAffected = deleteMenuPst.executeUpdate();

                    if (deleteMenuRowsAffected > 0) {
                        System.out.println("Menu deleted successfully");
                    } else {
                        System.out.println("Failed to delete menu");
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkMenuExists(int menuId) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM menu WHERE MenuID = ?");
        pst.setInt(1, menuId);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    private boolean checkMenuCanBeUpdated(int menuId) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM menu WHERE MenuID = ?");
        pst.setInt(1, menuId);
        ResultSet rs = pst.executeQuery();
        return !rs.next(); // Returns true if there are no orders for this menu
    }

    private boolean checkMenuCanBeDeleted(int menuId) throws SQLException {
        // You can implement this based on your business logic
        // For now, let's assume a menu can always be deleted
        return true;
    }
}
