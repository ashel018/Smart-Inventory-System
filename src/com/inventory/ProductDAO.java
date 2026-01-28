package com.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ProductDAO {

    static Scanner sc = new Scanner(System.in);

    // ADD PRODUCT
    public static void addProduct() {
        System.out.print("Enter product name: ");
        String name = sc.next();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        try (Connection con = DBConnection.getConnection()) {
            String sql =
                "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);

            ps.executeUpdate();
            System.out.println("✅ Product added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW PRODUCTS
    public static void viewProducts() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM products";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("ID | Name | Price | Quantity");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("product_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getDouble("price") + " | " +
                        rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE STOCK
    public static void updateStock() {
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();

        System.out.print("Enter new quantity: ");
        int qty = sc.nextInt();

        try (Connection con = DBConnection.getConnection()) {
            String sql =
                "UPDATE products SET quantity=? WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, qty);
            ps.setInt(2, id);
            ps.executeUpdate();

            System.out.println("✅ Stock updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE PRODUCT
    public static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = sc.nextInt();

        try (Connection con = DBConnection.getConnection()) {
            String sql =
                "DELETE FROM products WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("❌ Product deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
