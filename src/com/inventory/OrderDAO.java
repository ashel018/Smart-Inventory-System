package com.inventory;

import java.sql.*;
import java.util.Scanner;

public class OrderDAO {

    static Scanner sc = new Scanner(System.in);

    public static void placeOrder() {
        System.out.print("Enter product ID: ");
        int pid = sc.nextInt();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        try (Connection con = DBConnection.getConnection()) {

            String check =
                "SELECT price, quantity FROM products WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(check);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Product not found");
                return;
            }

            int stock = rs.getInt("quantity");
            double price = rs.getDouble("price");

            if (stock < qty) {
                System.out.println("❌ Not enough stock");
                return;
            }

            double total = price * qty;

            String orderSql =
                "INSERT INTO orders(product_id, quantity, total_price) VALUES (?, ?, ?)";
            PreparedStatement orderPs =
                con.prepareStatement(orderSql);

            orderPs.setInt(1, pid);
            orderPs.setInt(2, qty);
            orderPs.setDouble(3, total);
            orderPs.executeUpdate();

            String updateStock =
                "UPDATE products SET quantity=? WHERE product_id=?";
            PreparedStatement upd =
                con.prepareStatement(updateStock);

            upd.setInt(1, stock - qty);
            upd.setInt(2, pid);
            upd.executeUpdate();

            System.out.println("✅ Order placed. Total = " + total);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewOrders() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM orders";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nOrderID | ProductID | Qty | Total | Date");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("order_id") + " | " +
                        rs.getInt("product_id") + " | " +
                        rs.getInt("quantity") + " | " +
                        rs.getDouble("total_price") + " | " +
                        rs.getTimestamp("order_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
